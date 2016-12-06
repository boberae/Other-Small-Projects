//Tony Bober, Kyle Werner, Braden Distal
// Feedback control program

#include <Serial.h>      // This header file allows you to work with terminal window
#define c_kinetic_pos 5; /* static friction */
#define c_kinetic_neg 4; /* make the value positive */
#define c_static_pos 8;  /* kinetic friction */
#define c_static_neg 7;  /* make the value positive */
#define c_max 255
#define c_min 255        /* make the value positive */

char incomingByte = 0;   // for incoming serial data
int count = -255;
int PWMPin; 
int error = 0;
unsigned sensorValue = 0;
int adj_count;
int moving = 0;
int db_correct = 0;
int abs_count = 0;


// --------------- Setting directions for I/O ----------------------------

void AD_setup(void)
{
  pinMode(A0, OUTPUT);     //sets A0 as output
  pinMode(A1, OUTPUT);     //Sets A1 as an output
}

void PWM_setup(void)
{
  PWMPin = 5;              //sets pwm pin to pin 3
}

int deadband(int c_wanted){ /* call this routine when updating */
int c_pos;
int c_neg;
int c_adjusted;

if(moving == 1){
  c_pos = c_kinetic_pos;
  c_neg = c_kinetic_neg;
} 
else {
  c_pos = c_static_pos;
  c_neg = c_static_neg;
}
if(c_wanted == 0){      /* turn off the output */
  c_adjusted = 0;
} 
else if(c_wanted > 0){  /* a positive output */
  c_adjusted = c_pos + (unsigned)(c_max - c_pos) * c_wanted / c_max;
}
if(c_adjusted > c_max) {
  c_adjusted = c_max;
} 
else {                  /* the output must be negative */
  c_adjusted = c_neg +(unsigned)(c_min - c_neg) * c_wanted / c_min;
}
if(c_adjusted > c_min) {
  c_adjusted = c_min;
}
  return c_adjusted;
}

void IO_update(void)
{ 
   if (db_correct==0){
    analogWrite(PWMPin, abs_count);  //send motor new voltage   
   }
  else if (db_correct==1){
    adj_count = deadband(abs_count);
    analogWrite(PWMPin, adj_count);  //send motor new voltage
  }
}

void IO_setup(void)
{
 DDRB = 0xFF;              // Declare PORTB as O/P
 AD_setup(); 
 PWM_setup(); 
}

// ------------------------------ SETUP FUNCTION -------------------------
void setup(void)
{
 Serial.begin(9600);       // Opens serial port and sets the data rate to 9600 bits-per-second 
 Serial.println("---------------------------------------------");
 Serial.println("\n\rDeadBand Compensation application");
 Serial.println("---------------------------------------------");
 Serial.println("Press '+' to increment counter ");
 Serial.println("Press '-' to decrement counter ");
 Serial.println("Press 'p' to display counter value and deadband compensation status");
 Serial.println("Press 'd' to enable or disable (default) deadband compensation");
 Serial.println("Press 'h' to display HELP ");
 Serial.println("Press 'q' to quit ");
 IO_setup();   
}
// -------------------- LOOP FUNCTION -------------------------------
void loop()
{ 
  if (count == 0)
  {
    digitalWrite(A1, LOW);  //0V to PC1
    digitalWrite(A0, LOW);  //0V to PC0
  }  
  if(Serial.available()>0)          //Print data only when you receive data
  {
    incomingByte = Serial.read();   //Read the incoming byte
    if(incomingByte == '=')
    {
      Serial.print("You entered");
      Serial.print(incomingByte);
      Serial.println(". Sou counter incremented");
      count = count + 5;  
      if(count > 255)
       {count = 255;}
       if(count > 0){
        abs_count = count;       
        digitalWrite(A1, LOW);  //0V to PC1
        digitalWrite(A0, HIGH);  //5V to PC0
        if (count == 0)
        {
         moving = 0; 
         adj_count = 0;
        }  
        IO_update();
        delay(200);
        moving = 1;      
      }      
       if(count < 0){
        abs_count = count * -1;       
        digitalWrite(A1, HIGH);  //0V to PC1
        digitalWrite(A0, LOW);  //5V to PC0
         if (count == 0)
        {
         moving = 0; 
         adj_count = 0;
        }  
        IO_update();
        delay(200);
        moving = 1;      
      }
      IO_update();                  //Send new value to PWM through this subroutine
      Serial.println(count);
     Serial.print("adjusted count value: ");
    Serial.println(adj_count);
     } 
    else if(incomingByte == '-')
    {
      Serial.print("You entered");
      Serial.print(incomingByte);
      Serial.println(". So counter decremented");
      count = count - 5; 
      if(count < -255)
       {count = -255;}
      if(count < 0){
        abs_count = count * -1;             
        digitalWrite(A0, LOW);  //0V to PC0
        digitalWrite(A1, HIGH);  //5V to PC1
        if (count == 0)
        {
         moving = 0; 
         adj_count = 0;
        }  
        IO_update();
        delay(200);
        moving = 1;
      }
       if(count > 0){
        abs_count = count * 1;             
        digitalWrite(A0, HIGH);  //0V to PC0
        digitalWrite(A1, LOW);  //5V to PC1
        if (count == 0)
        {
         moving = 0;
        adj_count = 0; 
        }  
        IO_update();
        delay(200);
        moving = 1;
      }
      IO_update();                  //Send new value to PWM through this subroutine
      Serial.println(count);
     Serial.print("adjusted count value: ");
    Serial.println(adj_count);
    }
   else if(incomingByte == 'd'){
    if(db_correct == 0){
       db_correct = 1;
       Serial.print("You entered ");
       Serial.print(incomingByte);
       Serial.println(". So DeadBand Compensation is ON");
    } else {
       db_correct = 0;
       Serial.print("You entered ");
       Serial.print(incomingByte);
       Serial.println(".So DeadBand Compensation is OFF ");
    }
    IO_update();
    } 
    else if(incomingByte == 'p')
     { 
       Serial.print("Count is: ");
       Serial.println(count);
       Serial.print("Deadband is: ");
       Serial.println(db_correct);  
     }
   else if(incomingByte == 'h')
     {
      Serial.println("increment value");
      Serial.println(" -: decrement value");
      Serial.println(" d: enable or disable (default) deadband comp.");
      Serial.println(" s: stop the motor");
      Serial.println(" q: quit");
     }
   else if(incomingByte == 'q')
     {
      count = 0;
      moving = 0;
      IO_update(); 
      Serial.println("GOOD-BYE !!!");
      delay(1000);
      exit(1);
     }
  }  
}

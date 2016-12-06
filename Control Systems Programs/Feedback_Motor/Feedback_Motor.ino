#include <Serial.h>         // This header file allows you to work with terminal window

char incomingByte = 0;       // for incoming serial data
int count = 0;
int PWMPin; 
int error = 0;
int currentValue = 0;
int setPoint = 240 ;        // DESIRED VALUE
unsigned sensorValue = 0;
unsigned sensorValue2 = 0;
int gain = 10;              //MULTIPLIER VALUE
int flip = 0;              
int value[100];             //array of values

// --------------- Setting directions for I/O ----------------------------

void AD_setup(void)
{
  pinMode(A2, INPUT);     //Sets A2 as an input      
 //Serial.println((sensorValue * 196)/10);// prints the battery's voltage in MV 
  
}
void PWM_setup(void)
{
  PWMPin = 6;                       //sets pwm pin to pin 6
}
void IO_update(void)
{
  sensorValue = analogRead(A2);     //read tachometer output
  currentValue = sensorValue / 4;     //convert analog input to scale of PWM output
  error = (setPoint - currentValue) * gain; //calculate error  
  count = error;     //adjust count 
  flip = flip + 1;
  analogWrite(PWMPin, count);
  
}

void IO_setup(void)
{
 DDRB = 0xFF; // Declare PORTB as O/P
 AD_setup(); 
 PWM_setup(); 
}

// ---------------- Setting up ISR to execute every 10ms -----------------
void Timer1_ISR_setup(void)
{
   TCCR1A = 0;
   TCCR1B = _BV(CS10) | _BV(WGM12); // Divide-by-1 clock and CTC mode
   OCR1A = 16000; // A delay of 16000 ticks is 10ms -> 16MHz is 62.5ns 
   // So 16000 x 62.5ns -> 10ms
   TIFR1 = _BV(OCF1A); // Clear Output Compare Flag bit
   TIMSK1 = _BV(OCIE1A); // Setting the O/P Compare Interrupt bit 
   sei(); // Enable interrupt mechanism
}

// ISR gets executed every 10ms
ISR(TIMER1_COMPA_vect)
{
 IO_update();         //update code
}
// ------------------------------ SETUP FUNCTION -------------------------

void setup(void)
{
   Serial.begin(9600);           // Opens serial port and sets the data rate to 9600 bits-per-second 
   Serial.println("---------------------------------------------");
   Serial.println("\n\rThis is a FEEDBACK CONTROLLER application");
   Serial.println("---------------------------------------------");
   Serial.println("Press '+' to increment counter ");
   Serial.println("Press '-' to decrement counter ");
   Serial.println("Press 'p' to display counter value ");
   Serial.println("Press 'h' to display HELP ");
   Serial.println("Press 'q' to quit ");
   count = setPoint;
   IO_setup();
   Timer1_ISR_setup();   
}

// -------------------- LOOP FUNCTION -------------------------------
void loop()
{
  
 //Store and print every new tach voltage 
 while (flip < 200)
 {
   if (flip % 2 == 0)
   {
     for(int i = 0; i < 100; i++)
     {
       value[i] = currentValue;
       Serial.println(value[i]);
     }
   }
 }
 
 }
  if(Serial.available()>0)          //Print data only when you receive data
  {
    incomingByte = Serial.read();   //Read the incoming byte
    if(incomingByte == '+')
    {
      Serial.print("You entered");
      Serial.print(incomingByte);
      Serial.println(". Sou counter incremented");
      count = count + 20;
      if(count > 255)
       {count = 255;}
      Serial.print("New count");
      Serial.println(count);
      IO_update();                  //Send new value to PWM through this subroutine
    }
    else if(incomingByte == '-')
    {
      Serial.print("You entered");
      Serial.print(incomingByte);
      Serial.println(". So counter decremented");
      count = count - 20;
      if(count < 0) 
        {count = 0;}
      Serial.print("New count");
      Serial.println(count);
      IO_update();                  //Send new value to PWM through this subroutine
    }
    else if(incomingByte == 'p')
    {
      for(int i = 0; i< 100; i++)
      {
        sensorValue = analogRead(A2); //read tachometer output
        sensorValue2 = sensorValue2+ sensorValue;
    
      }
       Serial.println(sensorValue2/100);
       sensorValue2= 0;
    }
    else if(incomingByte == 'h')
    {
      Serial.println("HELP: +, -, p, q");
    }
    else if(incomingByte == 'q')
    {
      Serial.println("GOOD-BYE");
      delay(1000);
      exit(1);
    }

  }
  
}

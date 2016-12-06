const int serialPeriod = 100;       // only print to the serial console every 1/2 second
unsigned long timeSerialDelay = 0;

const int loopPeriod = 100;
unsigned long timeLoopDelay   = 0;

// specify the trig & echo pins used for the ultrasonic sensors
const int ultrasonic2TrigPin = 8;
const int ultrasonic2EchoPin = 9;

int ultrasonic2Distance;
int ultrasonic2Duration;
int lastDistance;

void setup()
{
    Serial.begin(9600);

    // ultrasonic sensor pin configurations
    pinMode(ultrasonic2TrigPin, OUTPUT);
    pinMode(ultrasonic2EchoPin, INPUT);
}


void loop()
{
    debugOutput(); // prints debugging messages to the serial console

    if(millis() - timeLoopDelay >= loopPeriod)
    {
        lastDistance = ultrasonic2Distance;
        readUltrasonicSensors(); // read and store the measured distances

        timeLoopDelay = millis();
    }
}


void readUltrasonicSensors()
{
    // ultrasonic 2
    digitalWrite(ultrasonic2TrigPin, HIGH);
    delayMicroseconds(10);                  // must keep the trig pin high for at least 10us
    digitalWrite(ultrasonic2TrigPin, LOW);

    ultrasonic2Duration = pulseIn(ultrasonic2EchoPin, HIGH);
    ultrasonic2Distance = (ultrasonic2Duration/2)/29;
}



void debugOutput()
{

    if((millis() - timeSerialDelay) > serialPeriod)
    {
        if(ultrasonic2Distance <= 30 && ultrasonic2Distance > 1 && lastDistance <= 30 && lastDistance > 1)
        {
           Serial.print("ultrasonic2Distance: ");
           Serial.print(ultrasonic2Distance);
           Serial.print("cm");
           Serial.println();
           Serial.print("OBJECT PRESENT \n");
        }

        timeSerialDelay = millis();
    }
}


int irRead(int readPin, int TriggerPin)
{
  digitalWrite(triggerPin, HIGH);
  delay(50);
  return analogRead(readPin);
}

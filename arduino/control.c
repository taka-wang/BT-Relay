#include <SoftwareSerial.h>

SoftwareSerial BTSerial(5, 6); // RX | TX

int RELAY_PIN = 2;  // Signal PIN
int state = 0;

void setup()
{
    pinMode(RELAY_PIN, OUTPUT);
    digitalWrite(RELAY_PIN, LOW);
    BTSerial.begin(38400); // Default communication rate of the Bluetooth module
}

void loop() 
{
    if (BTSerial.available() > 0) // Checks whether data is comming from the serial port
    {
        state = BTSerial.read();  // Reads the data from the serial port
    }
 
    if (state == '0')
    {
        digitalWrite(RELAY_PIN, LOW); // Set low signal to relay
        BTSerial.println("LOCK");
        state = 0;
    }
    else if (state == '1')
    {
        digitalWrite(RELAY_PIN, HIGH);
        BTSerial.println("UNLOCK");;
        state = 0;
    } 
}
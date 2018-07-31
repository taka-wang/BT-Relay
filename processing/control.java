import processing.serial.*;
Serial mPort;
String relayStatus = "LOCK";

void setup() 
{
    size(450, 300); // window size
    mPort = new Serial(this, "your-bluetooth-port", 38400);
    mPort.bufferUntil('\n');   // Defines up to '\n' or 'New Line'
}

void serialEvent(Serial myPort) 
{ 
    relayStatus = mPort.readStringUntil('\n'); 
}

void draw() 
{
    background(237, 240, 241);

    fill(88, 211, 247); // Button Color
    stroke(33);
    strokeWeight(1);
    rect(150, 100, 150, 50, 10); // Button
    fill(255);

    textSize(32);
    text("Trigger", 165, 135);

    textSize(24);
    fill(33);
    text("Status:", 180, 200);

    text(relayStatus, 180, 240); // Prints the string comming from the Arduino
    if (mousePressed && mouseX > 150 && mouseX < 300 && mouseY > 100 && mouseY < 150) 
    {
      mPort.write('1'); // Sends the character '1' and that will unlock the lock
      delay(200);       // 0.2s
      mPort.write('0'); // Sends the character '0' and that will lock the lock again

      stroke(255, 0, 0);
      strokeWeight(2);
      noFill();
      rect(150, 100, 150, 50, 10); // press effect
    }
}
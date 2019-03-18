
# JAVA E-ink demo for raspberry pi 

Demo spring boot application with h2 database, REST service and swagger. 
A message being entered by the user via REST service in the browser, will be stored in the database, and shown on a 4.2 epaper Waveshare display.

## Requirements
* Waveshare 4.2 epaper display https://www.waveshare.com/wiki/4.2inch_e-Paper_Module
* raspberry pi (with installed Java JDK, Maven and git. On Raspbian Java JDK and git are already installed, it is only necessary to install maven.)

## Todos
1. connect display to raspberry pi, pinout see displayPinout.png

2. enable SPI protocol on raspberry pi

3. download (clone) project, either on raspberry pi directly or on your local machine (on your local machine you can test the application, database and REST, but of course not the display).
Enter in the command line:

git clone https://github.com/ankesreich/eInkDemo.git

4. in the newly created directory "eInkDemo" build the application with:

mvn clean install

5. the "target" directory contains the file eInkDemo-0.0.1-SNAPSHOT.jar containing the whole application.
Start the application on the raspberry pi with: (see* for running headlessly)

java -Djava.awt.headless=false -jar -Dspring.profiles.active=raspi eInkDemo-0.0.1-SNAPSHOT.jar --debug

(on local machine replace raspi with local).

6. URL of the application (replace localhost with name of machine, if the call in the browser is not the same machine the application is running.)

http://localhost:8080/einkdemo/swagger-ui.html

7. URL of H2 console

http://localhost:8080/einkdemo/h2-console


## Tipps
* If you want to do more with Java on raspberry pi it is good idea to update the JDK. 
* The error message "busy Pin is too busy" indicates that the display is showing it is "busy". That might come as a surprise if you know that the display is not busy. 
A good idea then is to check the wiring. 

## Run test driver for image generation
By running the JUNIT test de.ankesreich.imagegeneration.MessageDisplayPanelTest you can test the image generation.

## * running einkDemo headlessly
JAVA Swing needs a running XServer, that means a gui must be running, to generate images which are then shown on the display.
I solved this by disabling the running XServer and starting an own XServer xvfb. 
(if you have a better solution, please let me know.)

### disabling LightDM
sudo update-rc.d lightdm disable

### install and start xvfb Xserver 
sudo apt-get install xvfb
Xvfb :1 -screen 0 800x600x16

### setting display variable before starting einkdemo
export DISPLAY=:1.0

java -Djava.awt.headless=false -jar -Dspring.profiles.active=raspi eInkDemo-0.0.1-SNAPSHOT.jar --debug




for more information:
www.ankesreich.de

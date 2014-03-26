/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author samwinkelstein
 */
// this class provides all communication with the DS.
public class IO {
public DriverStationEnhancedIO DSIO;
private Joystick driveStick;
private Joystick controlStick;
public static double DSMagnitude;
public static double DSdirection;
public static double DStwist;

public int curCommand;

public IO(RobotBase623 robotBase)
{
    driveStick = robotBase.getDriveStick();
    controlStick = robotBase.getControlStick();
}
    public void initDefaultCommand() {   
    }
    
     public int DSInput()       
    {
        int command = 0;
         
          try {
         
        DSIO = DriverStation.getInstance().getEnhancedIO();
  
        if(DSIO.getDigital(1))
        {
            command = 1;
        }
  
        if(DSIO.getDigital(2))
        {
           command = 2;
        }
         if(DSIO.getDigital(3))
        {
            command = 3;
          
        }
          if(DSIO.getDigital(4))
        {
            command = 4;
        }
            if(DSIO.getDigital(5))
        {
            command = 5;
        }
          
        } catch (DriverStationEnhancedIO.EnhancedIOException ex) {
        ex.printStackTrace();
}           curCommand = command;
          return command;
          
    }
     
     public void getDriveInput()
     {
         DSMagnitude =  driveStick.getMagnitude();
         DSdirection = driveStick.getDirectionDegrees();
         DStwist = driveStick.getTwist();
     }
     
     public void printToDash(int line, String str) {
        DriverStationLCD dsLCD = DriverStationLCD.getInstance();
        switch (line) {
            case 1:
                dsLCD.println(DriverStationLCD.Line.kUser1, 1, str);
                break;
            case 2:
                dsLCD.println(DriverStationLCD.Line.kUser2, 1, str);
                break;
            case 3:
                dsLCD.println(DriverStationLCD.Line.kUser3, 1, str);
                break;
            case 4:
                dsLCD.println(DriverStationLCD.Line.kUser4, 1, str);
                break;
            case 5:
                dsLCD.println(DriverStationLCD.Line.kUser5, 1, str);
                break;
            case 6:
                dsLCD.println(DriverStationLCD.Line.kUser6, 1, str);
                break;
        }
        dsLCD.updateLCD();
    }
     
}
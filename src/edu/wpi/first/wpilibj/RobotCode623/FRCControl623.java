
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

/**
 *
 * @author samwinkelstein
 */


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class FRCControl623 extends IterativeRobot {
  
    private int Vcounter;
    private double visionD;
    private PnuematicsControl airControl;
    private DriveTrain drivebase;
    public AutonomousController623 autoDrive;
    private RobotBase623 robotBase;
    public boolean PmoterStop = false;
    public boolean manPickup;
    public boolean manPickupR;
    
    // init Robot 
    
    public void robotInit() {
        robotBase = new RobotBase623();
        drivebase = new DriveTrain(robotBase);
        airControl = new PnuematicsControl(robotBase);
        VisionThread.init();
        manPickup = false;
        manPickupR = false;
    }

    public void testInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
        autoDrive.AutoInit();
    }

    public void autonomousPeriodic() {
        autoDrive.Auto1();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() 
    {
      airControl.retractPickup();
      airControl.PickupOut = false;
    }

    public void teleopPeriodic() {
        
        DSInput();
        if(PnuematicsControl.PickupOut &&!manPickup || manPickupR);
        {
        checkRetract();
        }
        if(airControl.stopMotor)
        {
            airControl.stopPickupMotor();
        }
        getUSoundDistance();
        getCamDistance();
       drivebase.drive();
    }
    
    //*************************************************
    //*************************************************
    /* This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
    public void disabledInit(){
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
    
        public void DSInput()       
    {
        int command = 0;
         
          try {
         
       DriverStationEnhancedIO DSIO = DriverStation.getInstance().getEnhancedIO();
  
        if(DSIO.getDigital(1))
        {
           airControl.Fire();
        }
  
        if(DSIO.getDigital(2))
        {
           airControl.Charge();
        }
         if(DSIO.getDigital(3))
        {
            autoDrive.JukeNShootRight();
          
        }
          if(DSIO.getDigital(4))
        {
            autoDrive.JukeNShootLeft();
        }
            if(DSIO.getDigital(5))
        {
            airControl.extendPickup();
            PmoterStop = false;
        }
            if(DSIO.getDigital(6))
            {
                airControl.extendPickup();
                manPickup = true;
            }
            else if(manPickup)
            {
                airControl.retractPickup();
                manPickup = false;
            }
             if(DSIO.getDigital(7))
            {
                airControl.extendPickupR();
                manPickup = true;
            }
             else if( manPickupR)
            {
                airControl.retractPickup();
                manPickupR = false;
            }
          
        } catch (DriverStationEnhancedIO.EnhancedIOException ex) {
        ex.printStackTrace();
        }
    }
          void checkRetract()
          {
              if(robotBase.getBallPieckupSwitch().get())
              {
                airControl.retractPickup();
                PmoterStop = true;
             }
              if(PmoterStop)
              {
                  airControl.retractPickup();
              }
             
          }
         public void getUSoundDistance()
          {
              double distance = robotBase.getEz4().getDistance();
              
              printToDash(1, " US distance " + distance );
          }
         public void getCamDistance()
         {
             printToDash(2, "CamY D" + Vision2.DistanceY);
             printToDash(3, "CamX D" + Vision2.DistanceX);
         }
         
          
    }


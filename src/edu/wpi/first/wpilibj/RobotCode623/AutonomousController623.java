/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author samwinkelstein
 */
public class AutonomousController623 {

    private RobotDrive mechDrive;
    private PnuematicsControl airControl;
    private double drivemagnitude;
    private double twistmagnitude;
    private double direction;
    private final int servoRight = 1;
    private final int servoLeft = -1;
    private int servo;
    public boolean start;
    public boolean rightScore;
    public boolean leftScore;
    public boolean ABORT;
    public RobotBase623 robotBase;
    public double ShootAngle;
    boolean initjukeNShoot;

    public AutonomousController623(RobotBase623 robotBase, PnuematicsControl airControl) {
        this.robotBase = robotBase;
        mechDrive = robotBase.getDriveBase();
        this.airControl = airControl;
        start = true;
        robotBase.getGyro().reset();
        ShootAngle = robotBase.getGyro().getAngle();
    }
      

    public boolean ServoRight() {
        if (servo == servoRight) {
            return true;
        }

        return false;
    }

    public boolean ServoLeft() {
        if (servo == servoLeft) {
            return true;
        }

        return false;
    }


    public void JukeNShootRight() {
        while(Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            }
                else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if(ABORT)
            {
                break;
            }
        }
       while(robotBase.getGyro().getAngle() < 15)
       {
           twistmagnitude = .7;
       if(ABORT)
       {
           break;
       }
        mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
       }
       mechDrive.mecanumDrive_Polar(0, 0, 0);
       Timer.delay(0.25);
       airControl.Fire();

    }
    
      public void JukeNShootLeft() {
        while(Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            }
                else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if(ABORT)
            {
                break;
            }
        }
       while(robotBase.getGyro().getAngle() > -15)
       {
           twistmagnitude = -.7;
       if(ABORT)
       {
           break;
       }
        mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
       }
       mechDrive.mecanumDrive_Polar(0, 0, 0);
          Timer.delay(0.25);
       airControl.Fire();

    }
    
    
    
}

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

    private final RobotDrive mechDrive;
    private final PnuematicsControl airControl;
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
    private int autoCycles;

    public AutonomousController623(RobotBase623 robotBase, PnuematicsControl airControl) {
        this.robotBase = robotBase;
        mechDrive = robotBase.getDriveBase();
        this.airControl = airControl;
        start = true;
        robotBase.getGyro().reset();
        ShootAngle = robotBase.getGyro().getAngle();
    }

    public void AutoInit() {
        ShootAngle = robotBase.getGyro().getAngle();
        autoCycles = 0;
        airControl.Charge();
    }
    private int shootTimer = 0;

    public void Auto1() {
        if (RC.shootDistanceMin < Vision2.DistanceY && Vision2.DistanceY < RC.shootDistanceMax
                || RC.shootDistanceMin < getUSoundDistance() && getUSoundDistance() < RC.shootDistanceMax) {

            double Magintude = 0;
            double Direction = 0;
            double twist = 0;
            mechDrive.mecanumDrive_Polar(Magintude, direction, twist);
            if (Vision2.isHot) {
                if (shootTimer == 3) {
                    airControl.Fire();
                } else {
                    shootTimer++;
                }
            }
        } else {
            double Magintude = .75;
            double Direction = 0;
            double twist = 0;
            mechDrive.mecanumDrive_Polar(Magintude, Direction, twist);
            shootTimer = 0;
        }
    }
    
    public void AutoSimple() {
        if (autoCycles >= 100) {
            mechDrive.mecanumDrive_Polar(0, 0, 0);
            airControl.Fire();
        } else {
            mechDrive.mecanumDrive_Polar(.75, 0, 0);
            autoCycles++;
        }
    }

    public boolean ServoRight() {
        return servo == servoRight;
    }

    public boolean ServoLeft() {
        return servo == servoLeft;
    }

    public void JukeNShootRight() {
        while (Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            } else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if (ABORT) {
                break;
            }
        }
        while (robotBase.getGyro().getAngle() < 15) {
            twistmagnitude = .7;
            if (ABORT) {
                break;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
        }
        mechDrive.mecanumDrive_Polar(0, 0, 0);
        Timer.delay(0.25);
        airControl.Fire();

    }

    public void JukeNShootLeft() {
        while (Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            } else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if (ABORT) {
                break;
            }
        }
        while (robotBase.getGyro().getAngle() > -15) {
            twistmagnitude = -.7;
            if (ABORT) {
                break;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
        }
        mechDrive.mecanumDrive_Polar(0, 0, 0);
        Timer.delay(0.25);
        airControl.Fire();

    }

    public double getUSoundDistance() {
        return robotBase.getEz4().getDistance();

    }

}

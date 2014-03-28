/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author samwinkelstein
 */
public class DriveTrain extends Subsystem {

    public void initDefaultCommand() {
        drive();
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private RobotDrive mechDrive;
    private Joystick driveStick;
    private Gyro gyro;
    private double DriveMag;
    private double direction;
    private double twistMag;
    private boolean ABORT;

    public DriveTrain(RobotBase623 base) {
        mechDrive = base.getDriveBase();
        driveStick = base.getDriveStick();
    }

    public void drive() {
        double magnitude = applyMagnitudeSquared(applyMagnitudeDeadband(driveStick.getMagnitude()));
        double direction = driveStick.getDirectionDegrees();
        double rotation = applyRotationalDeadband(driveStick.getTwist());
        mechDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    public void AutoDrive(double Magnitude, double direction, double twist) {
        mechDrive.mecanumDrive_Polar(Magnitude, direction, twist);
    }

    private double applyMagnitudeSquared(double magnitude) {
        return magnitude * magnitude;
    }

    // joystick Deadbands*****************************
    double applyMagnitudeDeadband(double magnitude) {
        double newMagnitude = magnitude;
        if (newMagnitude > .05) {
            newMagnitude = newMagnitude - .05;
        } else if (newMagnitude > 1) {
            newMagnitude = 1;
        }
        return newMagnitude;
    }

    double applyRotationalDeadband(double Twist) {
        double newTwist = Twist;

        if (newTwist > .1) {
            newTwist = newTwist - .1;
        } else if (newTwist < -.1) {
            newTwist = newTwist + .1;
        }
        return newTwist;
    }

}

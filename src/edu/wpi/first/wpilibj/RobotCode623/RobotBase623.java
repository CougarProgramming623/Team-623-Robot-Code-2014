/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author samwinkelstein
 */
public class RobotBase623 {

    //ALL pieces of hardware
    //joysticks
    private final Joystick driveStick;
    // Drive Base ******************
    private RobotDrive MechDrive;
    //DriveMotors
    private Talon right_Front_Motor;
    private Talon right_Back_Motor;
    private Talon left_Front_Motor;
    private Talon left_Back_Motor;
    private Talon PickupTalon;
    //******************************
    // Pnuematics components********
    private Compressor compressor;
    private DigitalInput ballPickupSwitch;
    //******************************
    public static VisionController visControl;
    //sensors Alog
    private Gyro gyro;
    private ultraSonic_Alog ez4;
    //constructor

    public RobotBase623() {
        InitDrive();
        InitSensors();
        driveStick = new Joystick(RC.DRIVER_JOYSTICK_PORT);
        
        System.out.println("ready for operation!");
        compressor.start();
        System.out.println("compressor started!"); 
    }
    // Drive Train Init;

    private void InitDrive() {
        // Talons init
        right_Front_Motor = new Talon(RC.Right_Front_Motor_PWM);
        right_Back_Motor = new Talon(RC.Right_Back_Motor_PWM);
        left_Front_Motor = new Talon(RC.Left_Front_Motor_PWM);
        left_Back_Motor = new Talon(RC.Left_Back_Motor_PWM);

        // RobotDrive Init();
        MechDrive = new RobotDrive(left_Front_Motor, left_Back_Motor, right_Front_Motor, right_Back_Motor);
        MechDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        MechDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }

    private void InitSensors() {
        gyro = new Gyro(RC.Gyro_ALog);
        ez4 = new ultraSonic_Alog(2, 1);
        ballPickupSwitch = new DigitalInput(RC.Ball_Pickup_DIO);
    }
    
    private void initVision() {
        visControl = VisionController.getInstance();
    }

    public Compressor getCompressor() {
        return compressor;
    }

    public Gyro getGyro() {
        return gyro;
    }

    public RobotDrive getDriveBase() {
        return MechDrive;
    }

    public Joystick getDriveStick() {
        return driveStick;
    }

    public DigitalInput getBallPickupSwitch() {
        return ballPickupSwitch;
    }

    public Talon GetPickupTalon() {
        return PickupTalon;
    }
    
    public DigitalInput getPickupSwitch() {
        return ballPickupSwitch;
    }
    
    public ultraSonic_Alog getEz4() {
        return ez4;
    }
}

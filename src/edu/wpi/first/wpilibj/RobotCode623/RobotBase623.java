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
    private Joystick driveStick;
    private Joystick controlStick;
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
    private DoubleActSol catch_Sol;
    private DoubleActSol pickup_Sol;
    private DoubleActSol holding_Sol;
    private SingleActSol launch_sol;
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
        InitPnuematics();
        driveStick = new Joystick(RC.DRIVER_JOYSTICK_PORT);
        controlStick = new Joystick(RC.Control_JOYSTICK_PORT);
        
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
        System.out.println("Drive Inititlized");
    }
    // Pnuematics Init;

    private void InitPnuematics() {
        catch_Sol = new DoubleActSol(RC.Catch_Lock_Port, RC.Catch_release_Port);
       pickup_Sol = new DoubleActSol(RC.Pickup_Extend_port, RC.Pickup_Retract_port);
        launch_sol = new SingleActSol(RC.Launch_Cylenders_Sol);
        compressor = new Compressor(RC.COMPRESSOR_PRESSURE_SWITCH_CHANNEL_DIO, RC.COMPRESSOR_RELAY_CHANNEL);
        System.out.println("Pnuematics Inititlized");
    }

    private void InitSensors() {
        gyro = new Gyro(RC.Gyro_ALog);
        ez4 = new ultraSonic_Alog(2, 1);
        ballPickupSwitch = new DigitalInput(RC.Ball_Pickup_DIO);
        
        System.out.println("Sensor Ports Inititlized");
    }
    private void initVision()
    {
        visControl = VisionController.getInstance();
    }

    public Compressor getCompressor() {
        return compressor;
    }

    public DoubleActSol getCatch() {
        return catch_Sol;
    }

    public DoubleActSol getPickup() {
        return pickup_Sol;
    }

    public DoubleActSol getHolder() {
        return holding_Sol;
    }

    public SingleActSol getLaunchCylenders() {
        return launch_sol;
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

    public Joystick getControlStick() {
        return controlStick;
    }
    public DigitalInput getBallPieckupSwitch() {
        return ballPickupSwitch;
    }
    public Talon GetPickupTalon()
    {
        return PickupTalon;
    }
    public DigitalInput getPickupSwitch()
    {
        return ballPickupSwitch;
    }
    public ultraSonic_Alog getEz4()
    {
        return ez4;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 * @author samwinkelstein
 */
// Robot control system

public class RC {
    // Drive Base with Talons use PWM at end
   public static final int Right_Front_Motor_PWM = 1;
   public static final int Right_Back_Motor_PWM = 2;
   public static final int Left_Front_Motor_PWM = 3;
   public static final int Left_Back_Motor_PWM = 4;
   
   //control Joysticks
    public static final int DRIVER_JOYSTICK_PORT = 1;
    
    //Joystick 1 buttons
    public static final int JSB1_FIRE_IN_RAnge = 1;
    public static final int JSB1_Spin_Right = 3;
    public static final int JSB1_Spin_Left = 4;
    
    // enhanced IO buttons
    public static final int DIO_Fire = 1;
    public static final int DIO_Charge = 2;
    public static final int DIO_Juke_shoot_right = 3;
    public static final int DIO_Juke_shoot_Left = 4;
    public static final int DIO_Pickup_auto = 5;
    public static final int DIO_Pickup_manual_extend = 6;
    public static final int DIO_Pickup_auto_reverse = 7;
    public static final int DIO_Pickup_manual_retract = 8;
    public static final int DIO_Pickup_rollers_reverse = 9;
    public static final int DIO_Pickup_rollers_forward = 10;
    
    //enhanced IO LEDs
    public static final int DIO_LED_Pressure_switch = 11;
    public static final int DIO_LED_In_range = 12;

    //Puematics System pnuematics ports 
    //compressor
    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL_DIO = 1;
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    
    //Catch values
    public static final Value Catch_Lock = Value.kForward;
    public static final Value Catch_Release = Value.kReverse;
    public static final Value Catch_off = Value.kOff;
     
    //pickup values
    public static final Value Pickup_extend = Value.kForward;
    public static final Value Pickup_retract = Value.kReverse;
    public static final Value Pickup_off = Value.kOff;
    
    public static final Value Launch_launch = Value.kForward;
    public static final Value Launch_retract = Value.kReverse;
    public static final Value Launch_off = Value.kOff;

    //solinoid single
    //public static final int Launch_Cylenders_Sol = 1;
    // solinoid double
    // catch sol
    public static final int Catch_Lock_Port = 2;
    public static final int Catch_release_Port = 3;
    // pickup sol
    public static final int Pickup_Extend_port = 4;
    public static final int Pickup_Retract_port = 5;
    // launch sol
    public static final int Launch_Extend_port = 7;
    public static final int Launch_Retract_port = 8;
    //anolog sensors use ALog at end 1, 8
    public static final int Gyro_ALog = 1;

    
    // Digital inputs use Alog at end 1,9
    
    //Ball pickup switch
    public static final int Ball_Pickup_DIO = 3;    
    
    //BallPickup talon
    public static final int Pickup_Talon_port = 5;
     
    //Vision
    public static final int minWidthforHot = 70;
    public static final int minHeightforHot = 70;
    public static final double shootDistanceMax = 12.5;
    public static final double shootDistanceMin = 11.5;
    public static final double CloseshootDistanceMax = 7.6;
    public static final double CloseshootDistanceMin = 5;
}


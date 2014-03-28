/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author samwinkelstein
 */
public class PnuematicsControl extends Subsystem {

    protected void initDefaultCommand() {
    }

    private final DoubleSolenoid Catch;
    private final DoubleSolenoid pickup;
    private final Solenoid LaunchCylenders;
    //private final Compressor compressor;
    public static boolean charged;
    public static boolean fire;
    private final Talon PickUpTalon;
    public boolean PickupOut;
    public boolean stopMotor;
    public int pickupCount = 0;

    public PnuematicsControl() {
        charged = false;
        Catch = new DoubleSolenoid(RC.Catch_Lock_Port, RC.Catch_release_Port);
        pickup = new DoubleSolenoid(RC.Pickup_Extend_port, RC.Pickup_Retract_port);
        LaunchCylenders = new Solenoid(RC.Launch_Cylenders_Sol);
        //compressor = new Compressor(RC.COMPRESSOR_PRESSURE_SWITCH_CHANNEL_DIO, RC.COMPRESSOR_RELAY_CHANNEL);
        //compressor.start();
        
        PickUpTalon = new Talon(5);
        PickUpTalon.set(0.0);
        PickupOut = false;
        stopMotor = false;
    }

    public void Charge() {
        Catch.set(RC.Catch_Lock);
        Timer.delay(1.5);
        LaunchCylenders.set(true);
        Timer.delay(10.0);
        charged = true;
    }

    public void Fire() {
        Catch.set(RC.Catch_Release);
        Timer.delay(.5);
        LaunchCylenders.set(false);
    }

    public void extendPickup() {
        pickup.set(RC.Pickup_extend);
        PickUpTalon.set(1.0);
        PickupOut = true;
    }

    public void extendPickupR() {
        pickup.set(RC.Pickup_extend);
        PickUpTalon.set(-1.0);
        PickupOut = true;
    }

    public void extendPickupN() {
        pickup.set(RC.Pickup_extend);
        PickupOut = true;
    }

    public void retractPickup() {
        pickup.set(RC.Pickup_retract);
        stopMotor = true;
    }

    public void stopPickupMotor() {
        if (pickupCount > 6) {
            PickUpTalon.set((0.0));
            stopMotor = false;
            pickupCount = 0;
        } else {
            pickupCount++;
        }
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

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
    private DoubleActSol Catch;
    private DoubleActSol pickup;
    private DoubleActSol holder;
    private SingleActSol LaunchCylenders;
    public static boolean charged;
    public static boolean fire;
    private Talon PickUpTalon;  
    public static boolean PickupOut; 
    public int pickupCount = 0;
    public PnuematicsControl(RobotBase623 robotBase) {
        charged = false;
        Catch = robotBase.getCatch();
        pickup = robotBase.getPickup();
        LaunchCylenders = robotBase.getLaunchCylenders();
        PickUpTalon = new Talon(5);
        PickUpTalon.set(0.0);
        PickupOut = false;
    }

    public void Charge() {
        Catch.setState(RC.Catch_Lock);
        Timer.delay(1.5);
        LaunchCylenders.setState(true);
        Timer.delay(10.0);
        charged = true;
    }

    public void Fire() {
        Catch.setState(RC.Catch_Release);
        Timer.delay(.5);
        LaunchCylenders.setState(false);
    }
    public void extendPickup() {
        
        pickup.setState(RC.Pickup_extend);
        PickUpTalon.set(1.0);
        PickupOut = true;
        
        
    }

    public void retractPickup() {
        pickup.setState(RC.Pickup_retract);
        PickupOut = true;
        if(pickupCount>=6)
        {
            PickupOut = false;
              PickUpTalon.set(0.0);
              pickupCount = 0;
        }
        
            pickupCount ++;
        }
    }

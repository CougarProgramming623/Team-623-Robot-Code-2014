/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author samwinkelstein
 */
public class SensorSS extends Subsystem {
    
    private ultraSonic_Alog ez4;
    private ultraSonic_Alog urm;

    public static double Ez4Range;
    public static double urmRange;
    
    public SensorSS()
    {
        ez4 = new ultraSonic_Alog(2, 1);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getDistance()
    {
        Ez4Range = ez4.getDistance();
        //urmRange = urm.getDistance();
        return Ez4Range;
    }
}
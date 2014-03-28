/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

/**
 *
 * @author samwinkelstein
 */
import edu.wpi.first.wpilibj.AnalogChannel;

public class ultraSonic_Alog extends AnalogChannel {
    private final double vpi;
    private static final double vpi1 = 0.0098;
    private static final double vpi2 = 0.00838;
    //ez4 = 1; urm = 2;
    public ultraSonic_Alog(int channel, int type) {
        super(channel);
        if (1 == type) {
            vpi = vpi1;
        } else {
            vpi = vpi2;
        }
    }
     // get distancein feet
    public double getDistance(){
        return (getVoltage()/vpi)/12;            
    }
}

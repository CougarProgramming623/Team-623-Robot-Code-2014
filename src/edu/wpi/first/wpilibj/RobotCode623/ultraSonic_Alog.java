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
import edu.wpi.first.wpilibj.DigitalOutput;

public class ultraSonic_Alog extends AnalogChannel {
    private int type;
    private static final double vpi1 = 0.0098;
     private static final double vpi2 = 0.00838;
     public DigitalOutput out;
    //ez4 = 1; urm = 2;
    public ultraSonic_Alog(int channel, int type)
    {
        
         super(channel);
        this.type = type;
       
    }
     // get distancein feet
    public double getDistance(){
        double vpi = 0;
        if(type == 1)
        {
            vpi = vpi1;
        }
        else
        {
            vpi = vpi2;
        }
        return (getVoltage()/vpi)/12;            
    }
    
    
}

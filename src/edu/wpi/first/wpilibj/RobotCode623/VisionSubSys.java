/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author samwinkelstein
 */
public class VisionSubSys extends Subsystem {
    
    public static boolean AutoVision;
    
    private double distance;
    private VisionController Vis;
    public double Distance;
  
    
    public VisionSubSys()
    {
        
    }
    protected void initDefaultCommand() {
        
    }
    
    public void init()
    {
        
    }
    public double DoVision()
    {
        Vis.processImage();
        distance = Vis.Distance;
        return distance;
        }     
    }
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author samwinkelstein
 */
public class VisionThread implements Runnable{
private VisionController Vis;
    public void run() {
        while(true)
        {
           Vis.processImage();
           
        }
    }
}

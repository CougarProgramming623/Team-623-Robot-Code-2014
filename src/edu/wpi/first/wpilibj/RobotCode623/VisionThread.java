/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;



/**
 *
 * @author samwinkelstein
 */
public class VisionThread implements Runnable{
    
    public static Vision2 VisionProcess= new Vision2();
    public void run() {
        while(true)
        {
           VisionProcess.DoVision();
        }
    }
        public static void init()
        {
            new Thread(new VisionThread()).start();
        }
    }

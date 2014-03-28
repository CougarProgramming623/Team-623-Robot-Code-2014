/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
 * @author User
 */
public class DSIOWrapper implements Runnable {

    public volatile short d = 0;
    public volatile boolean pressure_led = false;
    public volatile boolean range_led = false;
    
    public void run() {
        DriverStationEnhancedIO DSIO = null;
        while (true) {
            try {
                if (null == DSIO) {
                    DSIO = DriverStation.getInstance().getEnhancedIO();
                    //DSIO.setDigitalConfig(RC.DIO_LED_Pressure_switch, 
                    //        DriverStationEnhancedIO.tDigitalConfig.kOutput);
                    //DSIO.setDigitalConfig(RC.DIO_LED_In_range,
                    //        DriverStationEnhancedIO.tDigitalConfig.kOutput);
                }
                d = DSIO.getDigitals();
                DSIO.setDigitalOutput(RC.DIO_LED_Pressure_switch, pressure_led);
                DSIO.setDigitalOutput(RC.DIO_LED_In_range, range_led);
            } catch (Exception e) {
            }
            Thread.yield();
        }
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

/**
 *
 * @author samwinkelstein
 */
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class FRCControl623 extends IterativeRobot {

    private int Vcounter;
    private double visionD;
    private PnuematicsControl airControl;
    private DriveTrain drivebase;
    public AutonomousController623 autoDrive;
    private DSIOWrapper DSIO = new DSIOWrapper();
    //private DriverStationEnhancedIO DSIO = null;
    private RobotBase623 robotBase;
    private DriverStationLCD dsLCD = null;
    private boolean rollerStop = false;
    private boolean manPickup = false;
    private boolean manPickupR = false;
    private boolean rollersr = false;
    private boolean rollersf = false;

    // init Robot 
    public void robotInit() {
        robotBase = new RobotBase623();
        drivebase = new DriveTrain(robotBase);
        airControl = new PnuematicsControl();
        autoDrive = new AutonomousController623(robotBase, airControl);
        Vision2.init();
        new Thread(DSIO).start();
    }

    public void testInit() {
    }

    public void autonomousInit() {
        autoDrive.AutoInit();
    }

    public void autonomousPeriodic() {
        //autoDrive.Auto1();
        autoDrive.AutoSimple();
    }

    public void teleopInit() {
        airControl.retractPickup();
        airControl.PickupOut = false;
    }

    public void teleopPeriodic() {

        DSInput();
        if (airControl.PickupOut && !manPickup || manPickupR) {
            checkRetract();
        }
        if (airControl.stopMotor) {
            airControl.stopPickupMotor();
        }
        //getCamDistance();
        drivebase.drive();
    }

    public void testPeriodic() {
    }

    public void disabledInit() {
    }

    public void printToDash(int line, String str) {
        if (null == dsLCD) {
            dsLCD = DriverStationLCD.getInstance();
        }
        switch (line) {
            case 1:
                dsLCD.println(DriverStationLCD.Line.kUser1, 1, str);
                break;
            case 2:
                dsLCD.println(DriverStationLCD.Line.kUser2, 1, str);
                break;
            case 3:
                dsLCD.println(DriverStationLCD.Line.kUser3, 1, str);
                break;
            case 4:
                dsLCD.println(DriverStationLCD.Line.kUser4, 1, str);
                break;
            case 5:
                dsLCD.println(DriverStationLCD.Line.kUser5, 1, str);
                break;
            case 6:
                dsLCD.println(DriverStationLCD.Line.kUser6, 1, str);
                break;
        }
        dsLCD.updateLCD();
    }

    public void DSInput() {

        //try {
            //if (null == DSIO) {
            //    DSIO = DriverStation.getInstance().getEnhancedIO();
            //    DSIO.setDigitalConfig(RC.DIO_LED_Pressure_switch, 
            //            DriverStationEnhancedIO.tDigitalConfig.kOutput);
            //    DSIO.setDigitalConfig(RC.DIO_LED_In_range,
            //            DriverStationEnhancedIO.tDigitalConfig.kOutput);
            //}
            short d = DSIO.d;
            
            //if (DSIO.getDigital(RC.DIO_Fire)) {
            if (0 != (d & (1 << (RC.DIO_Fire - 1)))) {
                //printToDash(1, "fire!");
                airControl.Fire();
            //} else {
            //    printToDash(1, "FIRE!");
            }
            //if (DSIO.getDigital(RC.DIO_Charge)) {
            if (0 != (d & (1 << (RC.DIO_Charge - 1)))) {
                //printToDash(2, "charge!");
                airControl.Charge();
            //} else {
            //    printToDash(2, "CHARGE!");
            }
            //if (DSIO.getDigital(RC.DIO_Juke_shoot_right)) {
            //if (0 != (d & (1 << RC.DIO_Juke_shoot_right))) {
            //    autoDrive.JukeNShootRight();
            //}
            //if (DSIO.getDigital(RC.DIO_Juke_shoot_Left)) {
            //if (0 != (d & (1 << RC.DIO_Juke_shoot_Left))) {
            //    autoDrive.JukeNShootLeft();
            //}
            //if (DSIO.getDigital(RC.DIO_Pickup_auto)) {
            if (0 != (d & (1 << (RC.DIO_Pickup_auto - 1)))) {
                airControl.extendPickup();
                rollerStop = false;
            }
            //if (DSIO.getDigital(RC.DIO_Pickup_manual_extend)) {
            if (0 != (d & (1 << (RC.DIO_Pickup_manual_extend - 1)))) {
                airControl.extendPickupN();
                manPickup = true;
            } else if (manPickup) {
                airControl.retractPickup();
                manPickup = false;
            }
            //if (DSIO.getDigital(RC.DIO_Pickup_auto_reverse)) {
            //if (0 != (d & (1 << RC.DIO_Pickup_auto_reverse))) {
            //    airControl.extendPickupR();
            //    manPickupR = true;
            //} else if (manPickupR) {
            //    airControl.retractPickup();
            //    manPickupR = false;
            //}
            //if (DSIO.getDigital(RC.DIO_Pickup_manual_retract)) {
            if (0 != (d & (1 << (RC.DIO_Pickup_manual_retract - 1)))) {
                airControl.retractPickup();
            }
            //if (DSIO.getDigital(RC.DIO_Pickup_rollers_reverse)) {
            if (0 != (d & (1 << (RC.DIO_Pickup_rollers_reverse - 1)))) {
                rollersr = true;
                airControl.PickUpTalon.set(1.0);
            } else if (rollersr) {
                airControl.PickUpTalon.set(0.0);
                rollersr = false;
            }
            //if (DSIO.getDigital(RC.DIO_Pickup_rollers_forward)) {
            if (0 != (d & (1 << (RC.DIO_Pickup_rollers_forward - 1)))) {
                rollersf = true;
                airControl.PickUpTalon.set(-1.0);
            } else if (rollersf) {
                airControl.PickUpTalon.set(0.0);
                rollersf = false;
            }
            //DSIO.setDigitalOutput(RC.DIO_LED_Pressure_switch,
            //        robotBase.getCompressor().getPressureSwitchValue());
            //DSIO.setDigitalOutput(RC.DIO_LED_In_range, inRange());
            DSIO.pressure_led = robotBase.getCompressor().getPressureSwitchValue();
            DSIO.range_led = inRange();
        //} catch (DriverStationEnhancedIO.EnhancedIOException ex) {
        //    ex.printStackTrace();
        //}
    }

    void checkRetract() {
        if (robotBase.getBallPickupSwitch().get()) {
            airControl.retractPickup();
            rollerStop = true;
        }
        if (rollerStop) {
            airControl.retractPickup();
        }
    }

    public double getUSoundDistance() {
        return robotBase.getEz4().getDistance();
    }

    public void getCamDistance() {
        printToDash(2, "CamY D" + Vision2.DistanceY);
        printToDash(3, "CamX D" + Vision2.DistanceX);
    }

    public boolean inRange() {
        return RC.shootDistanceMin < Vision2.DistanceY &&
                Vision2.DistanceY < RC.shootDistanceMax ||
                RC.shootDistanceMin < getUSoundDistance() &&
                getUSoundDistance() < RC.shootDistanceMax;
    }
}
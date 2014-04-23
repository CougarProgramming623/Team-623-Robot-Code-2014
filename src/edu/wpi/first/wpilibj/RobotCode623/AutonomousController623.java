/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;


import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author samwinkelstein
 */
public class AutonomousController623 {

    private final RobotDrive mechDrive;
    private final PnuematicsControl airControl;
    private double drivemagnitude;
    private double twistmagnitude;
    private double direction;
    private final int servoRight = 1;
    private final int servoLeft = -1;
    private int servo;
    public boolean start;
    public boolean rightScore;
    public boolean leftScore;
    public boolean ABORT;
    public RobotBase623 robotBase;
    public double ShootAngle;
    boolean initjukeNShoot;
    private int autoCycles;
    public static boolean autoSnap;
    public Timer matchTimer;
    public static Vision2 snapshotVision;
    private int cycles;
    private boolean fired;
    private boolean automove;
    
    public void AutoInitforHG() {
       autoCycles = 0;
       fired = false;
       automove =  true;
   }

    public AutonomousController623(RobotBase623 robotBase, PnuematicsControl airControl) {
        this.robotBase = robotBase;
        mechDrive = robotBase.getDriveBase();
        this.airControl = airControl;
        start = true;
        robotBase.getGyro().reset();
        ShootAngle = robotBase.getGyro().getAngle();
        //snapshotVision = new Vision2();
    }

        public void AutoInit() {
        ShootAngle = robotBase.getGyro().getAngle();
        autoCycles = 0;
        airControl.Charge();
    }
        
    private int shootTimer = 0;

    public void Auto1() {
        if (RC.shootDistanceMin < getUSoundDistance() && getUSoundDistance() < RC.shootDistanceMax) {

            double Magintude = 0;
            double Direction = 0;
            double twist = 0;
            mechDrive.mecanumDrive_Polar(Magintude, Direction, twist);
           // if (Vision2.isHot) {
               // if (shootTimer == 3) {
                    airControl.Fire();
               // } else {
               // }
        }
        else {
            double Magintude = .75;
            double Direction = 0;
            double twist = 0;
            mechDrive.mecanumDrive_Polar(Magintude, Direction, twist);
            shootTimer = 0;
        }
    }
    
    public void AutoSimple() {
        if (autoCycles == 95) {
            mechDrive.mecanumDrive_Polar(0, 0, 0);
            autoCycles++;
        } else if (autoCycles == 110) {
           // airControl.Fire();
        } else {
            mechDrive.mecanumDrive_Polar(.75, 0, 0);
            autoCycles++;
        }
    }

    public boolean ServoRight() {
        return servo == servoRight;
    }

    public boolean ServoLeft() {
        return servo == servoLeft;
    }

    public void JukeNShootRight() {
        while (Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            } else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if (ABORT) {
                break;
            }
        }
        while (robotBase.getGyro().getAngle() < 15) {
            twistmagnitude = .7;
            if (ABORT) {
                break;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
        }
        mechDrive.mecanumDrive_Polar(0, 0, 0);
        Timer.delay(0.25);
        airControl.Fire();

    }

    public void JukeNShootLeft() {
        while (Math.abs(robotBase.getGyro().getAngle() - ShootAngle) > 1) {
            if (robotBase.getGyro().getAngle() > ShootAngle) {
                twistmagnitude = -.3;
            } else {
                twistmagnitude = .3;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
            if (ABORT) {
                break;
            }
        }
        while (robotBase.getGyro().getAngle() > -15) {
            twistmagnitude = -.7;
            if (ABORT) {
                break;
            }
            mechDrive.mecanumDrive_Polar(0, 0, twistmagnitude);
        }
        mechDrive.mecanumDrive_Polar(0, 0, 0);
        Timer.delay(0.25);
        airControl.Fire();

    }

    public double getUSoundDistance() {
        return robotBase.getEz4().getDistance();

    }
    
    public void AutoWithHotGoal()
    {
        if(getUSoundDistance() < RC.shootDistanceMax || autoCycles >= 70 || !automove)
        {
            automove = false;
            mechDrive.mecanumDrive_Polar(0.0, 0.0, 0.0);
            if(autoSnap)
            {
                autoSnap = false;
                //snapshotVision.AutoSnapShot();
                
                cycles = 0;
            }
            if(Vision2.HotGoal && !fired)
            {
                System.out.println("HotGoal!");
                if(cycles >= 10)
                {
                airControl.Fire();
                fired = true;
                }
                else
                {
                    cycles ++;
                }
            }
            else if(autoCycles >= 250 && !fired)
            {
                 System.out.println("NotHot!");
                airControl.Fire();
                fired = true;
            }
            }
        else if(automove){
            mechDrive.mecanumDrive_Polar(0.75, 0, 0);
        }
        autoCycles ++;
    }
    
     public void AutoWithNoHotGoal()
    {
        if( getUSoundDistance() < RC.CloseshootDistanceMax || !automove)
        {
            automove = false;
            mechDrive.mecanumDrive_Polar(0.0, 0.0, 0.0);
                if(cycles >= 10 && !fired){
                airControl.Fire();
                fired = true;
                }
                else
                {
                    cycles ++;
                }
        }
        else{
            mechDrive.mecanumDrive_Polar(0.75, 0, 0);
        }
        autoCycles ++;
    }

}

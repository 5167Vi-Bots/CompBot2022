package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain {
    private WPI_TalonFX backLeft;
    private WPI_TalonFX frontLeft;
    private WPI_TalonFX backRight;
    private WPI_TalonFX frontRight;
    private MecanumDrive robotDrive;

    private double gyroAngle = 0; // replace with AHRS
    
    private final double ticksPerInch = 2048; //1365 208
    private final double driveFeedForward = 0.07;// 0.07 doesn't move 8 does
    private final double steerFeedForward = 0.06;
    private final double kP = 0.03;

    //constructor for drive train
    public DriveTrain(int backLeftPort, int backRightPort, int frontLeftPort, int frontRightPort){
        backLeft = new WPI_TalonFX(backLeftPort);
        backRight = new WPI_TalonFX(backRightPort);
        frontLeft = new WPI_TalonFX(frontLeftPort);
        frontRight = new WPI_TalonFX(frontRightPort);
        frontRight.setInverted(true);
        backRight.setInverted(true);
        robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
    }

     public void drive(double x, double y, double z) {
            robotDrive.driveCartesian(x, y ,z);
    }

    public double getDriveFF() {
        return driveFeedForward;
    }

    public double getSteerFF() {
        return steerFeedForward;
    }

    public double getAngle() {
        return gyroAngle; // replace with AHRS
    }

    public void holdAngle(double drive, double strafe, int angle) {
        double error = -(getAngle() - angle);
        double rotate = error * kP;
    
        drive(drive, strafe, rotate);
    }
}

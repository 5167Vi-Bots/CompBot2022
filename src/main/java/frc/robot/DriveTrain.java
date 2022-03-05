package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain {
    private WPI_TalonFX backLeft;
    private WPI_TalonFX frontLeft;
    private WPI_TalonFX backRight;
    private WPI_TalonFX frontRight;
    private MecanumDrive robotDrive;
    private MecanumDriveKinematics kinematics;
    private Translation2d frontLeftTranslation, frontRightTranslation, backLeftTranslation, backRightTranslation;

    private double ticksPerInch = 2048; //1365 208

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
        //frontLeftTranslation = new Translation2d(x, y);
        kinematics = new MecanumDriveKinematics(frontLeftTranslation, frontRightTranslation, backLeftTranslation, backRightTranslation);
    }

     public void drive(double x, double y, double z) {
            robotDrive.driveCartesian(x, y ,z);
    }
}

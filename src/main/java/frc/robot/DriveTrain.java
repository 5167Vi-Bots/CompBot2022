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
    
    private double ticksPerInch = 1365;

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
}

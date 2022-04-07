package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain {
    private WPI_TalonFX backLeft;
    private WPI_TalonFX frontLeft;
    private WPI_TalonFX backRight;
    private WPI_TalonFX frontRight;
    private MecanumDrive robotDrive;

    private AHRS gyro; // replace with AHRS
   
    
    private final double ticksPerInch = 2048; //1365 208
    private final double driveFeedForward = 0.07;// 0.07 doesn't move 8 does
    private final double steerFeedForward = 0.06;
    private final double kP = 0.03;
    private final double ticksPerAngle = 500;

    //constructor for drive train
    public DriveTrain(int backLeftPort, int backRightPort, int frontLeftPort, int frontRightPort){
        backLeft = new WPI_TalonFX(backLeftPort);
        backRight = new WPI_TalonFX(backRightPort);
        frontLeft = new WPI_TalonFX(frontLeftPort);
        frontRight = new WPI_TalonFX(frontRightPort);
        frontRight.setInverted(true);
        backRight.setInverted(true);
        frontRight.config_kP(0, 1);
        frontLeft.config_kP(0, 1);
        backLeft.config_kP(0, 1);
        backRight.config_kP(0, 1);
        robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        gyro = new AHRS(Port.kUSB);
        gyro.calibrate();
        Timer.delay(5);
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
        return gyro.getYaw();
        //gyro.getBoardYawAxis().board_axis.toString(); // replace with AHRS
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void holdAngle(double drive, double strafe, int angle, double maxDrive) {
        double error = -(getAngle() - angle);
        double rotate = error * kP;
        
        if(rotate > maxDrive){
            rotate = maxDrive;
        } else if(rotate < -maxDrive){
            rotate = -maxDrive;
        }
        if (Math.abs(error) > 3) {
            drive(drive, strafe, rotate);
        }
    }

    public void holdAngleEncoder(double kP, int angle, boolean robotOn) {
        frontRight.setSelectedSensorPosition(0);
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);

        double position = angle * ticksPerAngle;

        frontRight.set(ControlMode.Position, -position);
        frontLeft.set(ControlMode.Position, position);
        backLeft.set(ControlMode.Position, position);
        backRight.set(ControlMode.Position, -position);

        while (robotOn && Math.abs(frontRight.getMotorOutputPercent()) > 0.3 || Math.abs(frontLeft.getMotorOutputPercent()) > 0.3) {
            frontRight.set(ControlMode.Position, -position);
            frontLeft.set(ControlMode.Position, position);
            backLeft.set(ControlMode.Position, position);
            backRight.set(ControlMode.Position, -position);
        }
    }
}

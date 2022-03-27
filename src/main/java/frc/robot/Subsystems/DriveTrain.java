package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase{
    private WPI_TalonFX backLeft;
    private WPI_TalonFX frontLeft;
    private WPI_TalonFX backRight;
    private WPI_TalonFX frontRight;
    private MecanumDrive robotDrive;
    private MecanumDriveKinematics kinematics;
    private DifferentialDriveKinematics diffKinematics;
    private MecanumDriveOdometry odometry;
    private MecanumDriveWheelSpeeds wheelSpeeds;
    private RamseteController ramsete;
    private MecanumDriveMotorVoltages voltages;

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

        /* Creating the kinematics class which uses the x and y offset from the center of the robot in meters.
            +X : Forward
            -X : Backward
            +Y : Left
            -Y : Right
        */
        kinematics = new MecanumDriveKinematics(
            new Translation2d(0.381, 0.381), // Front Left Location
            new Translation2d(0.381, -0.381), // Front Right Location
            new Translation2d(-0.381, 0.381), // Back Left Location
            new Translation2d(-0.381, -0.381)); // Back Right Location
        diffKinematics = new DifferentialDriveKinematics(Units.inchesToMeters(18));
        odometry = new MecanumDriveOdometry(kinematics, getHeading(), new Pose2d(10, 13.5, new Rotation2d())); // X: 10 | Y: 13.5 (Center of DS) | Theta: 0
        ramsete = new RamseteController(); // Uses baseline values for ramsete
        voltages = new MecanumDriveMotorVoltages();
    }

    @Override
    public void periodic() {
        wheelSpeeds = new MecanumDriveWheelSpeeds(
            velocityToMeters(frontLeft.getSelectedSensorVelocity()), 
            velocityToMeters(frontRight.getSelectedSensorVelocity()), 
            velocityToMeters(backLeft.getSelectedSensorVelocity()),
            velocityToMeters(backRight.getSelectedSensorVelocity()));

        odometry.update(getHeading(), wheelSpeeds);
    }

     public void drive(double x, double y, double z) {
            robotDrive.driveCartesian(x, y ,z);
    }

    public void resetOdometry(Pose2d pose) {
        odometry.resetPosition(pose, getHeading());
    }

    public MecanumDriveKinematics getMecanumKinematics() {
        return kinematics;
    }

    public DifferentialDriveKinematics getDifferentialKinematics() {
        return diffKinematics;
    }

    public RamseteController getRamseteController() {
        return ramsete;
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

    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(-gyroAngle); // invert ahrs.getAngle so clockwise is - and cc is +
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public double velocityToMeters(double velocity) {
        return velocity * Units.inchesToMeters(6) / 60;
    }

    public MecanumDriveWheelSpeeds getWheelSpeeds() {
        return wheelSpeeds;
    }

    public void mecanumVolts(double frontLeft, double frontRight, double backLeft, double backRight) {
        voltages.frontLeftVoltage = frontLeft;
        voltages.frontRightVoltage = frontRight;
        voltages.rearLeftVoltage = backLeft;
        voltages.rearRightVoltage = backRight;
    }

    public void differentialVolts(double left, double right) {
        frontLeft.setVoltage(left);
        backLeft.setVoltage(left);
        frontRight.setVoltage(right);
        backRight.setVoltage(right);
        robotDrive.feed();
    }

    public void holdAngle(double drive, double strafe, int angle) {
        double error = -(getAngle() - angle);
        double rotate = error * kP;
    
        drive(drive, strafe, rotate);
    }
}

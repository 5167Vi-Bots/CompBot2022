package frc.robot.Commands;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.trajectory.constraint.MecanumDriveKinematicsConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.Subsystems.DriveTrain;

public class ForwardAuto extends CommandBase {
    private DriveTrain drivetrain;

    DifferentialDriveVoltageConstraint voltageConstraint;
    TrajectoryConfig trajectoryConfig;
    Trajectory trajectory;
    RamseteCommand ramseteCommand;
    SimpleMotorFeedforward ff;

    public ForwardAuto(DriveTrain drivetrain) {
        this.drivetrain = drivetrain;

        ff = new SimpleMotorFeedforward(
            Constants.DriveConstants.ksVolts,
            Constants.DriveConstants.kvVoltSecondsPerMeter,
            Constants.DriveConstants.kaVoltSecondsSquaredPerMeter
        );

        voltageConstraint = new DifferentialDriveVoltageConstraint(ff, drivetrain.getDifferentialKinematics(), 10);

        trajectoryConfig = new TrajectoryConfig(
                Constants.DriveConstants.kMaxSpeedMetersPerSecond, 
                Constants.DriveConstants.kMaxAccelerationMetersPerSecondSquared
            )
            .setKinematics(drivetrain.getDifferentialKinematics())
            .addConstraint(voltageConstraint);
        
        trajectory = TrajectoryGenerator.generateTrajectory(
            // Inital Pose
            new Pose2d(0, 0, new Rotation2d()),
            // 'S' Curve Path
            List.of(new Translation2d(1,1), new Translation2d(2, -1)),
            // End 3 meters ahead of where we started facing forward again
            new Pose2d(3, 0, new Rotation2d()),
            // Add the trajectory config
            trajectoryConfig
            );

        ramseteCommand = new RamseteCommand(
            trajectory,
            drivetrain.getPose(),
            drivetrain.getRamseteController(),
            ff,
            drivetrain.getDifferentialKinematics(),
            drivetrain::getWheelSpeeds,
            new PIDController(Constants.DriveConstants.kPDriveVel, 0, 0),
            new PIDController(Constants.DriveConstants.kPDriveVel, 0, 0),
            drivetrain.setdifferentialVolts(0, 0),
            drivetrain);

        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetOdometry(trajectory.getInitialPose());
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0, 0);
    }
}
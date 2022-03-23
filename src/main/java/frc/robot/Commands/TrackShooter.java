package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Limelight;

public class TrackShooter extends CommandBase{
    private DriveTrain driveTrain;
    private Limelight shooterLimelight;

    public TrackShooter(DriveTrain driveTrain, Limelight shooterLimelight) {
        this.driveTrain = driveTrain;
        this.shooterLimelight = shooterLimelight;
        addRequirements(this.driveTrain, this.shooterLimelight);
    }

    @Override
    public void initialize() {
        driveTrain.drive(0, 0, 0);
    }

    @Override
    public void execute() {
        shooterLimelight.updateTracking(0, 0, driveTrain);
    }

    @Override
    public boolean isFinished() {
        return shooterLimelight.doneTargeting();
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0);
    }
}

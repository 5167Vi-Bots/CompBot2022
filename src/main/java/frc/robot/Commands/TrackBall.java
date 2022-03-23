package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Limelight;

public class TrackBall extends CommandBase{
    private DriveTrain driveTrain;
    private Limelight intakeLimelight;

    public TrackBall(DriveTrain driveTrain, double k_drive, double k_steer, double k_minError, double k_maxDrive) {
        // intakeLimelight = new Limelight("limelight-i", k_drive, k_steer, k_minError, k_maxDrive, false, false);
    }

    public TrackBall(DriveTrain driveTrain, Limelight intakeLimelight) {
        // intakeLimelight = new Limelight("limelight-i", .08, .01, .30, 0.3, false, false);
        this.driveTrain = driveTrain;
        this.intakeLimelight = intakeLimelight;
        addRequirements(this.driveTrain, this.intakeLimelight);
    }

    @Override
    public void initialize() {
        driveTrain.drive(0, 0, 0);
    }

    @Override
    public void execute() {
        intakeLimelight.updateTracking(0, 0, driveTrain);
    }

    @Override
    public boolean isFinished() {
        return intakeLimelight.doneTargeting();
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0);
    }
}

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveTrain;

public class Rotate extends CommandBase{
    private DriveTrain driveTrain;
    private int angle;

    public Rotate(DriveTrain driveTrain, int angle) {
        this.driveTrain = driveTrain;
        this.angle = angle;
        addRequirements(this.driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.drive(0, 0, 0);
    }

    @Override
    public void execute() {
        driveTrain.holdAngle(0, 0, angle);
    }

    @Override
    public boolean isFinished() {
        if (driveTrain.getAngle() < angle-2 || driveTrain.getAngle() > angle+2) return false;
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0);
    }
}

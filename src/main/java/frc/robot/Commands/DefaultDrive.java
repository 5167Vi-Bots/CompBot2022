package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.DriveTrain;

public class DefaultDrive extends CommandBase{
    private DriveTrain drivetrain;
    private DoubleSupplier fwd, str, rot;

    public DefaultDrive(DriveTrain drivetrain, DoubleSupplier fwd, DoubleSupplier str, DoubleSupplier rot) {
        this.drivetrain = drivetrain;
        this.fwd = fwd;
        this.str = str;
        this.rot = rot;
        addRequirements(this.drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.drive(fwd.getAsDouble(), str.getAsDouble(), rot.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(0, 0, 0);
    }
}

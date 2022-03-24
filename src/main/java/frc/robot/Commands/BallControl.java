package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Elevator;

public class BallControl extends CommandBase{
    private Elevator elevator;
    private boolean up;

    public BallControl(Elevator elevator, boolean up) {
        this.elevator = elevator;
        this.up = up;
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        elevator.off();
    }

    @Override
    public void execute() {
        if (up) elevator.up();
        else elevator.down();
    }

    @Override
    public void end(boolean interrupted) {
        elevator.off();
    }
}

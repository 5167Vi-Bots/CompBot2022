package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Elevator;

public class UpperControl extends CommandBase{
    private Elevator elevator;
    private boolean up;

    public UpperControl(Elevator elevator, boolean up) {
        this.elevator = elevator;
        this.up = up;
        addRequirements(this.elevator);
    }

    @Override
    public void execute() {
        if (up) elevator.upperUp();
        else elevator.upperDown();
    }

    @Override
    public void end(boolean interrupted) {
        elevator.upperOff();
    }

}

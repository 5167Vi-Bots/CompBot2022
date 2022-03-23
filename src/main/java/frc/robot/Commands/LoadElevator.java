package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Elevator;

public class LoadElevator extends CommandBase{
    private Elevator elevator;

    public LoadElevator(Elevator elevator) {
        this.elevator = elevator;
        addRequirements(this.elevator);
    }

    @Override
    public void initialize() {
        elevator.off();
    }

    @Override
    public void execute() {
        elevator.lowerUp();
    }

    
    @Override
    public boolean isFinished() {
        return elevator.hasBall();
    }
    

    @Override
    public void end(boolean interrupted) {
        elevator.off();
    }
}

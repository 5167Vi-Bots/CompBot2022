package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Catapult;
import frc.robot.Subsystems.Elevator;

public class LoadCatapult extends CommandBase{
    private Elevator elevator;
    private Catapult catapult;

    public LoadCatapult(Elevator elevator, Catapult catapult) {
        this.elevator = elevator;
        this.catapult = catapult;
        addRequirements(this.elevator, this.catapult);
    }

    @Override
    public void initialize() {
        elevator.off();
    }

    @Override
    public void execute() {
        // if (!elevator.hasBall()) {
        //     new LoadElevator(elevator);
        // } else {
        //     elevator.upperUp();
        // }
        elevator.loadCatapult(catapult.hasBall());
    }

    
    @Override
    public boolean isFinished() {
        return catapult.hasBall();
    }
    

    @Override
    public void end(boolean interrupted) {
        elevator.off();
    }
}

package frc.robot.Commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.AutoShoot;
import frc.robot.Commands.LoadCatapult;
import frc.robot.Commands.LoadElevator;
import frc.robot.Subsystems.Catapult;
import frc.robot.Subsystems.Elevator;

public class LoadAndShoot extends SequentialCommandGroup{
    public LoadAndShoot(Elevator elevator, Catapult catapult) {
        addCommands(
            new LoadCatapult(elevator, catapult)//.alongWith(new AutoShoot(catapult))
        );
    }
}

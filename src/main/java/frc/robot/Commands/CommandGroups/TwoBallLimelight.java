package frc.robot.Commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.AutoShoot;
import frc.robot.Commands.LoadCatapult;
import frc.robot.Commands.LoadElevator;
import frc.robot.Commands.Rotate;
import frc.robot.Commands.TrackBall;
import frc.robot.Commands.TrackShooter;
import frc.robot.Subsystems.Catapult;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Elevator;
import frc.robot.Subsystems.Limelight;

public class TwoBallLimelight extends SequentialCommandGroup{
    public TwoBallLimelight(DriveTrain driveTrain, Limelight intakeLimelight, Limelight shooterLimelight, Catapult catapult, Elevator elevator) {
        addCommands(
            new LoadElevator(elevator).withTimeout(.5),

            (new TrackBall(driveTrain, intakeLimelight).alongWith(new LoadElevator(elevator))).withTimeout(3),

            new Rotate(driveTrain, -150).withTimeout(1),

            new TrackShooter(driveTrain, shooterLimelight),

            new AutoShoot(catapult).withTimeout(1),

            new LoadCatapult(elevator, catapult).withTimeout(1.5),

            new AutoShoot(catapult).withTimeout(1)
        );
    }
}

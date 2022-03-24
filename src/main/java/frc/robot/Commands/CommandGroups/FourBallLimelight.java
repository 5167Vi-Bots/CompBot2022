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

public class FourBallLimelight extends SequentialCommandGroup{
    public FourBallLimelight(DriveTrain drivetrain, Limelight intakeLimelight,
    Limelight shooterLimelight, Catapult catapult, Elevator elevator) {
        addCommands(
            new TwoBallLimelight(drivetrain, intakeLimelight, shooterLimelight, catapult, elevator).withTimeout(8.5), // Shoot first two

            new Rotate(drivetrain, 0).withTimeout(1), // Rotate for collection of other two

            (new TrackBall(drivetrain, intakeLimelight).alongWith(new LoadElevator(elevator))).withTimeout(2), // Drive towards other two

            new LoadCatapult(elevator, catapult).withTimeout(2), // Load two balls

            new Rotate(drivetrain, -140).withTimeout(1), // Rotate for shoots

            new TrackShooter(drivetrain, shooterLimelight).withTimeout(1.5),

            new AutoShoot(catapult).withTimeout(1),

            new LoadCatapult(elevator, catapult).withTimeout(1),

            new AutoShoot(catapult).withTimeout(1)
        );
    }
}

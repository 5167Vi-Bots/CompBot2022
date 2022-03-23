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
    public FourBallLimelight(DriveTrain driveTrain, Catapult catapult,
    Elevator elevator, Limelight intakeLimelight, Limelight shooterLimelight) {
        addCommands(
            new TwoBallLimelight(driveTrain, intakeLimelight, shooterLimelight, catapult, elevator).withTimeout(8.5), // Shoot first two

            new Rotate(driveTrain, 0).withTimeout(1), // Rotate for collection of other two

            (new TrackBall(driveTrain, intakeLimelight).alongWith(new LoadElevator(elevator))).withTimeout(2), // Drive towards other two

            new LoadCatapult(elevator, catapult).withTimeout(2), // Load two balls

            new Rotate(driveTrain, -140).withTimeout(1), // Rotate for shoot

            new TrackShooter(driveTrain, shooterLimelight).withTimeout(1.5),

            new AutoShoot(catapult).withTimeout(1),

            new LoadCatapult(elevator, catapult).withTimeout(1),

            new AutoShoot(catapult).withTimeout(1)
        );
    }
}

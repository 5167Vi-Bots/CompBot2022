package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Catapult;

public class AutoShoot extends CommandBase {
    private Catapult catapult;
    private boolean hasBall;

    public AutoShoot(Catapult catapult) {
        this.catapult = catapult;
        hasBall = false;
        addRequirements(this.catapult);
    }

    @Override
    public void initialize() {
        if (catapult.hasBall()) hasBall = true;
    }

    @Override
    public void execute() {
        if (hasBall) {
            catapult.shoot();
        }
        Timer.delay(.85);
        end(false);
    }

    @Override
    public void end(boolean interrupted) {
        catapult.stop();
    }
}

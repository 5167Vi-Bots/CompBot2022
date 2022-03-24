package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Lift;

public class LiftControl extends CommandBase{
    private Lift lift;
    private int liftSetting;
    private boolean up;

    public LiftControl(Lift lift, boolean up, int liftSetting) {
        this.lift = lift;
        this.liftSetting = liftSetting;
        this.up = up;
        addRequirements(this.lift);
    }

    @Override
    public void initialize() {
        lift.stop();
    }

    @Override
    public void execute() {
        if (up) {
            switch(liftSetting) {
                case 0:
                    lift.highUpPostion();
                    break;
                case 1:
                    lift.lowUpPosition();
                    break;
                case 2:
                    lift.up();
                    break;
            }
        } else {
            switch(liftSetting) {
                case 0:
                    lift.highDownPosition();
                    break;
                case 1:
                    lift.lowDownPosition();
                    break;
                case 2:
                    lift.down();
                    break;
            }
        }
    }
}

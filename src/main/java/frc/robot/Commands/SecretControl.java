package frc.robot.Commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.SecretWeapon;

public class SecretControl extends CommandBase{
    private SecretWeapon sw;
    private boolean forward;

    public SecretControl(SecretWeapon sw, boolean forward) {
        this.sw = sw;
        this.forward = forward;
        addRequirements(this.sw);
    }

    @Override
    public void initialize() {
        sw.off();
    }

    @Override
    public void execute() {
        if (forward) sw.activate();
        else sw.deactivate();
    }
}

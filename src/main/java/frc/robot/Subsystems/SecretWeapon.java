package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SecretWeapon extends SubsystemBase{

    DoubleSolenoid doubleSolenoid;

    public void k_swSolenoid (int forward, int back) {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forward, back);
    }

    public void activate(){
        doubleSolenoid.set(Value.kForward);
    }
    public void deactivate(){
        doubleSolenoid.set(Value.kReverse);
    }
}

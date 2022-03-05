package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class SecretWeapon {

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

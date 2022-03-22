package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Compressor;

public class SecretWeapon {

    DoubleSolenoid doubleSolenoid;
    Compressor compressor;
 
    public SecretWeapon (int forward, int back) {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forward, back);
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    }

    public void activate(){
        doubleSolenoid.set(Value.kForward);
    }
    public void deactivate(){
        doubleSolenoid.set(Value.kReverse);
    }
}

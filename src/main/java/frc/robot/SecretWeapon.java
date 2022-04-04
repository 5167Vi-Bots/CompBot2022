package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Compressor;

public class SecretWeapon {

    DoubleSolenoid doubleSolenoid;
    Compressor compressor;
    boolean isActive;
 
    public SecretWeapon (int forward, int back) {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forward, back);
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        isActive = false;
    }

    public void activate(){
        doubleSolenoid.set(Value.kForward);
        isActive = true;
    }
    public void deactivate(){
        doubleSolenoid.set(Value.kReverse);
        isActive = false;
    }
    public boolean getActive() {
        return isActive;
    }
}

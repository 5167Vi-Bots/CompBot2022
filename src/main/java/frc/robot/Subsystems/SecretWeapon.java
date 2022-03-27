package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SecretWeapon extends SubsystemBase{

    Compressor c;
    DoubleSolenoid doubleSolenoid;

    public SecretWeapon (int forward, int back) {
        doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forward, back);
        c = new Compressor(PneumaticsModuleType.CTREPCM);
    }

    public void activate(){
        doubleSolenoid.set(Value.kForward);
    }
    public void deactivate(){
        doubleSolenoid.set(Value.kReverse);
    }
    public void off() {
        doubleSolenoid.set(Value.kOff);
    }
}

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Catapult extends SubsystemBase{
    TalonFX catapultMotor;
    private DigitalInput catapultSwitch;

    public Catapult (int catapultPort, int switchPort) {
        catapultMotor = new TalonFX(catapultPort); 
        catapultSwitch = new DigitalInput(0);
    }
    
    public void stop(){
    catapultMotor.set (ControlMode.PercentOutput, 0);
    }

    public void shoot (){
    catapultMotor.set(ControlMode.PercentOutput, .70);
    }

    public boolean hasBall() {
        return !catapultSwitch.get();
    }

//    private void shootWithTimer (){
//   Boolean catapultLaunch;
//     Timer CatapultTimer = new Timer();
//     catapultLaunch = false;
//     TalonFX catapult = new TalonFX (int catapult);

//     if (catapultLaunch == false) CatapultTimer.start();
//   if (CatapultTimer.get() < 1) {
//     catapultLaunch = true;
//     catapult.set(ControlMode.PercentOutput, .42); //starts shooting here 
//   } else if (CatapultTimer.get() < 1.4) {
//     catapultLaunch = true;
//     catapult.set(ControlMode.PercentOutput, 0);// starts stoping here 
//   } else { // resets here
//     catapultLaunch = false;
//     CatapultTimer.stop();
//     CatapultTimer.reset();
//    }

// }
}
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Catapult {
    TalonFX catapultMotor;

    public Catapult (int catapultPort) {
        catapultMotor = new TalonFX(catapultPort); 
    }
    
    public void stop(){
    catapultMotor.set (ControlMode.PercentOutput, 0);
    }

    public void shoot (){
    catapultMotor.set(ControlMode.PercentOutput, .42);
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
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake {
    VictorSPX intake;
    
    public Intake(int intakePort){
     intake = new VictorSPX(intakePort);
    }

    public void in(){
     intake.set(ControlMode.PercentOutput, 0.75); //.5 // .75
    }

    public void stop(){
     intake.set(ControlMode.PercentOutput, 0);
    }

    public void out(){
     intake.set(ControlMode.PercentOutput, -.4);
    }
}
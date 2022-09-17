package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX intake;
    
    public Intake(int intakePort){
     intake = new TalonSRX(intakePort);
    }

    public void in(){
     intake.set(ControlMode.PercentOutput, -0.75); //.5 // .75
    }

    public void stop(){
     intake.set(ControlMode.PercentOutput, 0);
    }

    public void out(){
     intake.set(ControlMode.PercentOutput, .4);
    }
}
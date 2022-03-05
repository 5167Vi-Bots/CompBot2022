package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class Elevator {
    private VictorSPX upper;
    private VictorSPX lower;

    public Elevator(int lowerPort, int upperPort) {
        lower = new VictorSPX(lowerPort);
        upper = new VictorSPX(upperPort);
    }

    //turns off the motors that control the elevator system
    public void off() {
        upper.set(ControlMode.PercentOutput, 0);
        lower.set(ControlMode.PercentOutput, 0);
    }

    //sends the ball up the elevator system
    public void up() {
        upper.set(ControlMode.PercentOutput, .55);
        lower.set(ControlMode.PercentOutput, .45);
    }

    //sends the ball down the elevator system
    public void down() {
        upper.set(ControlMode.PercentOutput, -.55);
        lower.set(ControlMode.PercentOutput, -.45);
    }

}


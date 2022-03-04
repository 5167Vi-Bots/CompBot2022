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

    public void off() {
        upper.set(ControlMode.PercentOutput, 0);
        lower.set(ControlMode.PercentOutput, 0);
    }

    public void up() {
        upper.set(ControlMode.PercentOutput, .55);
        lower.set(ControlMode.PercentOutput, .45);
    }

    public void down() {
        upper.set(ControlMode.PercentOutput, -.55);
        lower.set(ControlMode.PercentOutput, -.45);
    }

}


package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Lift {
    private WPI_TalonSRX motor;

    public Lift(int motorPort) {
        motor = new WPI_TalonSRX(motorPort);
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        motor.config_kP(0, 1);
        motor.config_kI(0, 0);
        motor.config_kD(0, 0);
    }
    public void up() {
        motor.set(ControlMode.PercentOutput, .10);
    }

    public void down() {
        motor.set(ControlMode.PercentOutput, -.20);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

}
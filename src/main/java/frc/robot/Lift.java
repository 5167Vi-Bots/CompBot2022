package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Lift {
    private WPI_TalonFX hangerMotor;

    public Lift(int motorPort) {
        hangerMotor = new WPI_TalonFX(motorPort);
        hangerMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        hangerMotor.config_kP(0, 1);
        hangerMotor.config_kI(0, 0);
        hangerMotor.config_kD(0, 0);
    }
    public void up() {
        hangerMotor.set(ControlMode.Velocity, 10);
    }

    public void down() {
        hangerMotor.set(ControlMode.Velocity, -20);
    }

    public void setZero() {
        hangerMotor.set(ControlMode.Velocity, 0);
    }

}
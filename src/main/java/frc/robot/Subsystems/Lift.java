package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase{
    private WPI_TalonSRX motor;

    public Lift(int motorPort) {
        motor = new WPI_TalonSRX(motorPort);
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        motor.config_kP(0, 1);
        motor.config_kI(0, 0);
        motor.config_kD(0, 0);
        motor.configClosedLoopPeakOutput(0, 0.35);
        motor.setSelectedSensorPosition(0);
    }

    public double getPosition() {
        return motor.getSelectedSensorPosition();
    }

    public void highUpPostion() {
        motor.set(ControlMode.Position, 34000);
    }

    public void highDownPosition() {
        motor.set(ControlMode.Position, 20000);
    }

    public void lowUpPosition() {
        motor.set(ControlMode.Position, 0);
    }

    public void lowDownPosition() {
        motor.set(ControlMode.Position, 0);
    }

    public void up() {
        motor.set(ControlMode.PercentOutput, .70);
    }

    public void down() {
        motor.set(ControlMode.PercentOutput, -.35);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

}
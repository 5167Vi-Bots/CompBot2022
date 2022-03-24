package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Elevator extends SubsystemBase{
    private VictorSPX upper;
    private VictorSPX lower;
    private VictorSPX intake;
    private DigitalInput elevatorSwitch;

    public Elevator(int lowerPort, int upperPort, int intakePort) {
        lower = new VictorSPX(lowerPort);
        upper = new VictorSPX(upperPort);
        intake = new VictorSPX(intakePort);
        elevatorSwitch = new DigitalInput(1);
        intake.follow(lower);
    }

    //turns off the motors that control the elevator system
    public void off() {
        upper.set(ControlMode.PercentOutput, 0);
        lower.set(ControlMode.PercentOutput, 0);
    }

    //sends the ball up the elevator system
    public void up() {
        upper.set(ControlMode.PercentOutput, .75);
        lower.set(ControlMode.PercentOutput, .55);
    }

    //sends the ball down the elevator system
    public void down() {
        upper.set(ControlMode.PercentOutput, -.55);
        lower.set(ControlMode.PercentOutput, -.45);
    }

    public void loadCatapult(boolean catapultHasBall) {
        if (!catapultHasBall && hasBall()) {
            upperUp();
        } else if (!catapultHasBall && !hasBall()) {
            lowerUp();
            upperUp();
        } else {
            off();
        }
    }

    public void lowerUp() {
        lower.set(ControlMode.PercentOutput, .55);
    }

    public void lowerDown() {
        lower.set(ControlMode.PercentOutput, -.55);
    }

    public void lowerOff() {
        lower.set(ControlMode.PercentOutput, 0);
    }

    public void upperUp() {
        upper.set(ControlMode.PercentOutput, .75);
    }

    public void upperDown() {
        upper.set(ControlMode.PercentOutput, -.25);
    }

    public void upperOff() {
        upper.set(ControlMode.PercentOutput, 0);
    }

    public boolean hasBall() {
        return elevatorSwitch.get();
    }

}


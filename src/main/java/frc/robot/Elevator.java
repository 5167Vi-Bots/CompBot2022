package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Elevator extends SubsystemBase{
    private VictorSPX upper;
    private VictorSPX lower;
    private VictorSPX intake;
    private ColorSensorV3 proximitySensor;

    public Elevator(int lowerPort, int upperPort, int intakePort) {
        lower = new VictorSPX(lowerPort);
        upper = new VictorSPX(upperPort);
        intake = new VictorSPX(intakePort);
        proximitySensor = new ColorSensorV3(I2C.Port.kOnboard);
        intake.follow(lower);
    }

    //turns off the motors that control the elevator system
    public void off() {
        upperOff();
        lowerOff();
    }

    //sends the ball up the elevator system
    public void up() {
        upperUp();
        lowerUp();
    }

    //sends the ball down the elevator system
    public void down() {
        upperDown();
        lowerDown();
    }

    public void loadCatapult(boolean catapultHasBall) {
        if (!catapultHasBall) { // catapult needs a ball and needs to run entire elevator
            upperUp();
            lowerUp();
        } else {
            upperOff();
        }

        if (!hasBall()) { // elevator needs a ball and needs to run lower elevator
            lowerUp();
        } else {
            lowerOff();
        }
    
    }

    public void lowerUp() {
        setLowerSpeed(.65);
    }

    public void lowerDown() {
        setLowerSpeed(-.45);
    }

    public void lowerOff() {
        setLowerSpeed(0);
    }

    public void upperUp() {
       setUpperSpeed(.75);
    }

    public void upperDown() {
        setUpperSpeed(-.35);
    }

    public void upperOff() {
        setUpperSpeed(0);
    }

    public boolean hasBall() {
        return proximitySensor.getProximity() > 95;
    }

    public int getProx() {
        return proximitySensor.getProximity();
    }

    public void setLowerSpeed(double speed) {
        lower.set(ControlMode.PercentOutput, speed);
    }

    public void setUpperSpeed(double speed) {
        upper.set(ControlMode.PercentOutput, speed);
    }

}


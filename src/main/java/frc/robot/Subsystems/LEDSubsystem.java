package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase{
    private Spark leds;

    public LEDSubsystem(int port) {
        leds = new Spark(port);
    }

    public void setColor(double colorToSet) {
        if (leds.get() != colorToSet) {
            leds.set(colorToSet);
        }
    }

    public double getColor() {
        return leds.get();
    }


}

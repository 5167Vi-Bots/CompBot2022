package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.LEDSubsystem;

public class LED extends CommandBase{
    private LEDSubsystem leds;
    private double color;
    
    public LED(LEDSubsystem leds, double color) {
        this.leds = leds;
        this.color = color;
        addRequirements(this.leds);
    }

    @Override
    public void initialize() {
        leds.setColor(color);
        end(false);
    }
}

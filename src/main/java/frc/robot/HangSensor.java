package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class HangSensor {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch colorMatcher = new ColorMatch();
    
    private Color blueTarget = new Color(0.143, 0.427, 0.429);
    private Color greenTarget = new Color(0.197, 0.561, 0.240);
    private Color redTarget = new Color(0.561, 0.232, 0.114);
    private Color yellowTarget = new Color(0.361, 0.524, 0.113);
    private Color blackTarget = new Color(.24, .47, .27);
    private Color groundTarget = new Color(.25, .48, .26);
    

    public HangSensor (){
        colorMatcher.addColorMatch(blueTarget);
        colorMatcher.addColorMatch(greenTarget);
        colorMatcher.addColorMatch(redTarget);
        colorMatcher.addColorMatch(yellowTarget);
        colorMatcher.addColorMatch(blackTarget);
        colorMatcher.addColorMatch(groundTarget);
    }

    public boolean detectedBlue(){
        if (colorSensor.getColor() == blueTarget){
         return true;
        } else {
         return false;
        }
    }
    public boolean detectedRed(){
        if (colorSensor.getColor() == redTarget){
            return true;
        } else {
            return false;
        }
    }
    public boolean detectedYellow(){
        if (colorSensor.getColor() == yellowTarget){
            return true;
        } else {
            return false;
        }
    }
    public boolean detectedGreen(){
        if (colorSensor.getColor() == greenTarget){
            return true;
        } else {
            return false;
        }
    }
    public boolean detectedBlack(){
        if (colorSensor.getColor() == blackTarget){
            return true;
        } else {
            return false;
        }
    }
    public boolean detectedGround(){
        if (colorSensor.getColor() == groundTarget){
            return true;
        } else {
            return false;
        }
    }
}

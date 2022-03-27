package frc.robot;

public class Constants {

    public static final class Ports {
      public static final int k_backLeft = 4;
      public static final int k_backRight = 3;
      public static final int k_frontLeft = 1;
      public static final int k_frontRight = 2;
      public static final int k_elevatorLower = 3;
      public static final int k_elevatorUpper = 1;
      public static final int k_catapult = 6;
      public static final int k_catapultSwitch = 0;
      public static final int k_intake = 2;
      public static final int k_forward = 0;
      public static final int k_reverse = 1;
      public static final int k_climb = 7;
      public static final int k_leds = 0;
    }

    public static final class Colors {
        // Solid Colors
        public static final double kRed = 0.61;
        public static final double kGold = 0.67;
        public static final double kBlue = 0.87;
        public static final double kWhite = 0.93;
        public static final double kGreen = 0.77;
        public static final double kOrange = 0.65;
        public static final double kHotPink = 0.57;
        public static final double kSkyBlue = 0.83;
        public static final double kDarkBlue = 0.85;
        public static final double kDarkRed = 0.59;
        public static final double kDarkGreen = 0.75;
        // Strobe Colors
        public static final double kStrobeRed = -0.11;
        public static final double kStrobeBlue = 0.09;
        public static final double kStrobeGold = -0.07;
        public static final double kStrobeWhite = -0.05;
    
        // Breathing Colors
        public static final double kBreathingRed = -0.17;
        public static final double kBreathingGray = -0.13;
        public static final double kBreathingBlue = -0.15;
        public static final double kBreathingWhite = -0.21;
    
        // Heartbeat Colors
        public static final double kHeartbeatRed = -0.25;
        public static final double kHeartbeatGray = -0.19;
        public static final double kHeartbeatBlue = -0.23;
        public static final double kHeartbeatWhite = -0.21;
      }

      public static final class DriveConstants {
        // NOTE ALL KINEMATICS AND ODOMETRY VALUES INCLUDING THE ONES BELOW NEED TO BE TUNED THROUGH ROBOT CHARACTERIZATION
        public static final double ksVolts = 0.22; 
        public static final double kvVoltSecondsPerMeter = 1.98;
        public static final double kaVoltSecondsSquaredPerMeter = 0.2;
        public static final double kPDriveVel = 8.5;
        public static final double kMaxSpeedMetersPerSecond = 3; // Max robot Velocity we choose for how fast the trajectories can be followed
        public static final double kMaxAccelerationMetersPerSecondSquared = 3; // Max robot Acceleration we choose for how fast we accelerate in trajectories
      }

}

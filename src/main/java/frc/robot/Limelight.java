package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private String name;
    private double tv, tx, ty, ta;
    private boolean limelightHasValidTarget;
    private double limelightDriveCommand;
    private double limelightSteerCommand;

    public Limelight(String name) {
        this.name = name;
        limelightDriveCommand = 0.0;
        limelightSteerCommand = 0.0;
        limelightHasValidTarget = false;
    }

    public double getV() {
        tv = NetworkTableInstance.getDefault().getTable(name).getEntry("tv").getDouble(0);
        return tv;
    }

    public double getX() {
        tx = NetworkTableInstance.getDefault().getTable(name).getEntry("tx").getDouble(0);
        return tx;
    }

    public double getY() {
        ty = NetworkTableInstance.getDefault().getTable(name).getEntry("ty").getDouble(0);
        return ty;
    }

    public double getA() {
        ta = NetworkTableInstance.getDefault().getTable(name).getEntry("ta").getDouble(0);
        return ta;
    }

    public void updateTracking(double k_drive, double k_steer, double k_maxDrive, double strafe, DriveTrain driveTrain) {
        
        if (getV() < 1.0)
        {
          limelightHasValidTarget = false;
          limelightDriveCommand = 0.0;
          limelightSteerCommand = 0.0;
          return;
        }

        limelightHasValidTarget = true;

        double steer_cmd = getX() * k_steer;
        limelightSteerCommand = steer_cmd;

        double drive_cmd = getY() * k_drive;

       
        if (drive_cmd > k_maxDrive)
        {
          drive_cmd = k_maxDrive;
        }
        limelightDriveCommand = -drive_cmd;

        driveTrain.drive(limelightDriveCommand, strafe, limelightSteerCommand);
    }

}
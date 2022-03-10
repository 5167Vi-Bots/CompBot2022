// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  XboxController driveStick;
  XboxController controlStick;
  DriveTrain drivetrain;
  Elevator elevator;
  Intake intake;
  Catapult catapult;
  Limelight shooterLimelight;
  Limelight intakeLimelight;
  Lift lift;
  private Timer autoTimer;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    System.out.println("(o o)");
    System.out.println("/___\\");
    System.out.println("bob is going to competition!!!!!");
    System.out.println("this makes bob excited :]");

    autoTimer = new Timer();
    drivetrain = new DriveTrain(Constants.k_backLeft, Constants.k_backRight, Constants.k_frontLeft, Constants.k_frontRight);
    elevator = new Elevator(Constants.k_elevatorLower, Constants.k_elevatorUpper);
    catapult = new Catapult(Constants.k_catapult);
    intake = new Intake(Constants.k_intake);
    shooterLimelight = new Limelight("limelight-s", 0.17, 0.015, 0.25, 0.5, true, false);
    intakeLimelight = new Limelight("limelight-i", .08, .01, .30, 0.30, false, false);

    lift = new Lift(Constants.k_climb);

    driveStick = new XboxController(0);
    controlStick = new XboxController(1);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("tx", shooterLimelight.getX());
    SmartDashboard.putNumber("ty", shooterLimelight.getY());
    SmartDashboard.putBoolean("Shooter: Has Target", shooterLimelight.hasTarget());
    SmartDashboard.putBoolean("Intake: Has Target", intakeLimelight.hasTarget());
    SmartDashboard.putNumber("Drive", intakeLimelight.getDriveCommand());
    SmartDashboard.putNumber("Steer", intakeLimelight.getSteerCommand());
    SmartDashboard.putNumber("Lift Position", lift.getPosition());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    autoTimer.reset();
    autoTimer.start();
  }


  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
      if ( 4.0 > autoTimer.get() ) {
        intakeLimelight.updateTracking(0, 0, drivetrain);
      }if (5.50 > autoTimer.get()) {
        intake.in();
      } else {
        intake.stop();
      }
      
      if (4.50 > autoTimer.get() && 4.0 < autoTimer.get() || 9.0 < autoTimer.get() && 11.0 > autoTimer.get()) {
        elevator.up();
      } else if(4.50 < autoTimer.get() && 4.60 > autoTimer.get()) {
        elevator.down();
      } else {
        elevator.off();
      } if (4.5 < autoTimer.get() && 8.0 > autoTimer.get()) {
          shooterLimelight.updateTracking(0, 0, drivetrain);
      } if ((8.0 < autoTimer.get() && 9.0 > autoTimer.get()) || (11.0 < autoTimer.get() && 12.0 > autoTimer.get())) {
          catapult.shoot();
      } else {
          catapult.stop();
      }
    }
    
  
  
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}
  /** This function is called periodically during operator control. */

  @Override
  public void teleopPeriodic() {
    if (driveStick.getAButton()){
      shooterLimelight.updateTracking(0, (driveStick.getLeftX()/2), drivetrain); // Shooter Tracking
    } else if (driveStick.getBButton()){
      intakeLimelight.updateTracking(0, 0, drivetrain); // Intake Tracking
    } else {
      drivetrain.drive(-driveStick.getLeftY(), driveStick.getLeftX(), driveStick.getRightX()); // DriveTrain Drive
      //drivetrain.drive(0.08, 0, -0.06);
    }
  
    if (controlStick.getLeftBumper()) {
      lift.highDownPosition();
    }else if (controlStick.getRightBumper()) {
      lift.highUpPostion();
    }else {
      lift.stop();
    }
  
    
    if (controlStick.getYButton() == true){ // Elevator Up
      elevator.up();
      intake.in();
    } else if (controlStick.getXButton()) { // Elevator Down
      elevator.down();
    } else {
      intake.stop();
      elevator.off();
       // Intake off
    }

    if (controlStick.getBButton()) {
      catapult.shoot();
       // Catapult Shoot
    } else {
      catapult.stop();
       // Catapult Off
    }

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    if (controlStick.getLeftBumper()) {
      lift.down();
    } else if (controlStick.getRightBumper()) {
      lift.up();
    } else {
      lift.stop();
    }
  }
}

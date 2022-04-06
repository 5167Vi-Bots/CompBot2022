// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
  Catapult catapult;
  Limelight shooterLimelight;
  Limelight intakeLimelight;
  Lift lift;
  SecretWeapon secretWeapon;
  private Timer autoTimer;
  SendableChooser<Integer> liftChooser;
  private boolean isDone = false;
  ShuffleboardTab tele = Shuffleboard.getTab("Tele-op");
  
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

    // liftChooser = new SendableChooser<>();
    // liftChooser.addOption("manual",0);
    // liftChooser.addOption("Low Bar",1);
    // liftChooser.setDefaultOption("High Bar",2);

    drivetrain = new DriveTrain(Constants.k_backLeft, Constants.k_backRight, Constants.k_frontLeft, Constants.k_frontRight);
    elevator = new Elevator(Constants.k_elevatorLower, Constants.k_elevatorUpper, Constants.k_intake);
    catapult = new Catapult(Constants.k_catapult, Constants.k_catapultSwitch);
    shooterLimelight = new Limelight("limelight-s", 0.17, 0.015, 0.25, 1, true, false);
    intakeLimelight = new Limelight("limelight-i", .17, .01, .30, 0.5, false, false);
    secretWeapon = new SecretWeapon(Constants.k_swForward, Constants.k_swReverse);
    autoTimer = new Timer();
    liftChooser = new SendableChooser<>();

    lift = new Lift(Constants.k_climb);

    driveStick = new XboxController(0);
    controlStick = new XboxController(1);
    intakeLimelight.setPipe(0); // 1 for red 0 for blue
    liftChooser.addOption("Manual", 0);
    liftChooser.addOption("Low Bar", 1);
    liftChooser.setDefaultOption("High Bar", 2);
    SmartDashboard.putData("Lift Setting", liftChooser);
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
    SmartDashboard.putNumber("intake pipe", intakeLimelight.getPipe());
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getAngle());
    SmartDashboard.putBoolean("Secret Weapon Active", secretWeapon.getActive());
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
    intakeLimelight.setPipe(0); // 1 for red 0 for blue
    autoTimer.reset();
    autoTimer.start();
  }


  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    /*
      if ( 4.0 > autoTimer.get() ) {
        intakeLimelight.updateTracking(0, 0, drivetrain);
      } if (5.50 > autoTimer.get()) {
        intake.in();
      } else {
        intake.stop();
      }
      
      if (4.50 > autoTimer.get() && 4.0 < autoTimer.get() || 9.0 < autoTimer.get() && 12.0 > autoTimer.get()) {
        elevator.up();
      } else if(4.50 < autoTimer.get() && 4.60 > autoTimer.get()) {
        elevator.down();
      } else {
        elevator.off();
      } if (4.5 < autoTimer.get() && 8.0 > autoTimer.get()) {
          shooterLimelight.updateTracking(0, 0, drivetrain);
      } if ((8.0 < autoTimer.get() && 9.0 > autoTimer.get()) || (12.0 < autoTimer.get() && 13.0 > autoTimer.get())) {
          catapult.shoot();
      } else {
          catapult.stop();
      } */

      /*
      if (1 > autoTimer.get()) {
        elevator.lowerUp();
      } else if (5 > autoTimer.get()) {
          isDone = false;
      } else if (7 > autoTimer.get()) {
          elevator.lowerOff();
          if (!isDone) {
            drivetrain.holdAngleEncoder(1, 140, isAutonomousEnabled());
          }
          isDone = true;
      } else if (9 > autoTimer.get()) {
          shooterLimelight.updateTracking(0, 0, drivetrain);
      } else if (10 > autoTimer.get()) {
          catapult.shoot();
      } else if (11 > autoTimer.get()) {
          catapult.stop();
          elevator.up();
      } else if (12 > autoTimer.get()) {
          elevator.off();
          catapult.shoot();
      } else {
          catapult.stop();
          elevator.off();;
      } */

      if (0.5 > autoTimer.get()) {
        elevator.lowerDown();
      } else if (3 > autoTimer.get()) {
        elevator.lowerOff();
        intakeLimelight.updateTracking(0, 0, drivetrain);
        elevator.lowerUp();
      } else if (4 > autoTimer.get()) {
        elevator.lowerOff();
        drivetrain.holdAngle(0, 0, 162, 0.70);
      } else if (6.75 > autoTimer.get()) {
        shooterLimelight.updateTracking(0, 0, drivetrain);
      } else if (7.75 > autoTimer.get()) {
        catapult.shoot();
      } else if (9.75 > autoTimer.get()) {
        catapult.stop();
        elevator.upperUp();
      } else if (10.75 > autoTimer.get()) {
        catapult.shoot();
      } else if (12.35 > autoTimer.get()){
        catapult.stop();
        elevator.off();
      } else {
        catapult.stop();
        elevator.off();
        drivetrain.drive(0, 0, 0);
      }

      // if (3 > autoTimer.get()) {
      //   drivetrain.drive(-.3,0,0);
      // } else if (7 > autoTimer.get()) {
      //   shooterLimelight.updateTracking(0, 0, drivetrain);
      // } else if (8 > autoTimer.get()) {
      //   catapult.shoot();
      // } else {
      //   catapult.stop();
      // }
  }
    
  
  
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    lift.resetPosition();
    intakeLimelight.setPipe(0); // 1 for red 0 for blue
  }
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
  
    if (controlStick.getRightBumper()) {
      switch (liftChooser.getSelected()) {
        case 0:
          lift.down();
          break;
        case 1:
          lift.lowDownPosition();
          break;
        case 2:
          lift.highDownPosition();
          break; 
      }
    } else if (controlStick.getLeftBumper()) {
      switch (liftChooser.getSelected()){
        case 0:
          lift.up();
          break;
        case 1:
          lift.lowUpPosition();
          break;
        case 2:
          lift.highUpPostion();
          break;
      }
    } else {
        lift.stop();
    }

    // PNEUMATICS CODE
    if (driveStick.getLeftBumper()) {
      secretWeapon.activate();
    } else { 
      secretWeapon.deactivate();
    }
  
    
    if (controlStick.getYButton() == true){ // Elevator Up
      elevator.up();
    } else if (controlStick.getXButton()) { // Elevator Down
      elevator.down();
    } else if (driveStick.getBButton()) {
      elevator.lowerUp();
    } else {
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
    SmartDashboard.putBoolean("Switch", catapult.hasBall());
    // if (driveStick.getAButton()) {
    //   intake.in();
    //   if (!catapult.hasBall()) {
    //     elevator.up();
    //   } else {
    //     elevator.upperDown();
    //     elevator.lowerUp();
    //   } 
    // } else {
    //   intake.stop();
    //   elevator.off();
    // }

    if (driveStick.getAButton()) {
      elevator.lowerUp();
      intakeLimelight.updateTracking(0, 0, drivetrain);
    } else if(driveStick.getBButton()) {
      drivetrain.holdAngle(0, 0, 155, 0.75);
    } else if (driveStick.getXButton()) {
      drivetrain.holdAngle(0, 0, 0, 0.75);
    } else {
      drivetrain.drive(0, 0, 0);
    }
  }
}

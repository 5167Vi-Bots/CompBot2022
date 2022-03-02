// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  MecanumDrive robotDrive;
  XboxController driveStick;
  XboxController controlStick;
  WPI_TalonFX leftFront, leftBack, rightFront, rightBack, catapult;
  double ticksPerInch = 1365;
  VictorSPX intake;
  VictorSPX elevatorLower;
  VictorSPX elevatorUpper;
  
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
    leftFront = new WPI_TalonFX(1);
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    leftBack = new WPI_TalonFX(4);
    leftBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightFront = new WPI_TalonFX(2);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rightBack = new WPI_TalonFX(3);
    rightBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    driveStick = new XboxController(0);
    controlStick = new XboxController(1);
    rightFront.setInverted(true);
    rightBack.setInverted(true);
    leftBack.setInverted(false);
    leftFront.setInverted(false);
    robotDrive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftBack.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightBack.setNeutralMode(NeutralMode.Brake); 
    intake = new VictorSPX(2);
    elevatorLower = new VictorSPX(3);
    elevatorUpper = new VictorSPX(1); 
    catapult = new WPI_TalonFX(6);
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
    SmartDashboard.putNumber("leftFrontEncoder", leftFront.getSelectedSensorPosition());
    SmartDashboard.putNumber("leftBackEncoder", leftBack.getSelectedSensorPosition());
    SmartDashboard.putNumber("rightFrontEncoder", rightFront.getSelectedSensorPosition());
    SmartDashboard.putNumber("rightBackEncoder", rightBack.getSelectedSensorPosition());
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
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}
  
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    robotDrive.driveCartesian(-driveStick.getLeftY(), driveStick.getLeftX(), driveStick.getRightX());
    // if (driveStick.getAButton() == true){
    //   navX.reset();
    //   leftFront.setSelectedSensorPosition(0);
    //   leftBack.setSelectedSensorPosition(0);
    //   rightFront.setSelectedSensorPosition(0);
    //   rightBack.setSelectedSensorPosition(0);
    // } 
    if (controlStick.getYButton() == true){
      elevatorUpper.set(ControlMode.PercentOutput, .55);
      elevatorLower.set(ControlMode.PercentOutput, .45);
      
    } else if (controlStick.getXButton()) {
      elevatorUpper.set(ControlMode.PercentOutput, -.55);
      elevatorLower.set(ControlMode.PercentOutput, -.45);
    } else {
      elevatorUpper.set(ControlMode.PercentOutput, 0);
      elevatorLower.set(ControlMode.PercentOutput, 0);
    } if (controlStick.getAButton() == true){
      intake.set(ControlMode.PercentOutput, .5);
    } else {
      intake.set(ControlMode.PercentOutput, 0);
    }

    if (controlStick.getBButton()) {
      catapult.set(ControlMode.PercentOutput, 0.42);
    } else {
      catapult.set(ControlMode.PercentOutput, 0);
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
  public void testPeriodic() {}

  public void driveDistance(int inches) {
    double setPosition = ticksPerInch * inches;
    leftFront.set(ControlMode.Position, setPosition);
    leftBack.set(ControlMode.Position, setPosition);
    rightFront.set(ControlMode.Position, setPosition);
    rightBack.set(ControlMode.Position, setPosition);

  }
}

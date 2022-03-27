/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.BallControl;
import frc.robot.Commands.DefaultDrive;
import frc.robot.Commands.LED;
import frc.robot.Commands.LoadCatapult;
import frc.robot.Commands.SecretControl;
import frc.robot.Commands.TrackShooter;
import frc.robot.Commands.CommandGroups.FourBallLimelight;
import frc.robot.Commands.CommandGroups.TwoBallLimelight;
import frc.robot.Subsystems.Catapult;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Elevator;
import frc.robot.Subsystems.LEDSubsystem;
import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.SecretWeapon;
import frc.robot.Subsystems.VikingTab;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  // The robot's subsystems
  private final DriveTrain drivetrain = new DriveTrain(Constants.Ports.k_backLeft, Constants.Ports.k_backRight, Constants.Ports.k_frontLeft, Constants.Ports.k_frontRight);
  private final Elevator elevator = new Elevator(Constants.Ports.k_elevatorLower, Constants.Ports.k_elevatorUpper, Constants.Ports.k_intake);
  private final Catapult catapult = new Catapult(Constants.Ports.k_catapult, Constants.Ports.k_catapultSwitch);
  private final Limelight shooterLimelight = new Limelight("limelight-s", 0.17, 0.015, 0.25, 1, true, false);
  private final Limelight intakeLimelight = new Limelight("limelight-i", .08, .01, .30, 0.3, false, false);
  private final Lift lift = new Lift(Constants.Ports.k_climb);
  private final LEDSubsystem led = new LEDSubsystem(Constants.Ports.k_leds);
  private final SecretWeapon secretWeapon = new SecretWeapon(Constants.Ports.k_forward, Constants.Ports.k_reverse);
  private final VikingTab autoTab = new VikingTab("Autonomous");
  private final VikingTab teleTab = new VikingTab("Tele-op");


  XboxController driverController = new XboxController(0);
  XboxController operatorController = new XboxController(1);

  // Auton commands
  private final Command twoBallLimelight = new TwoBallLimelight(drivetrain, intakeLimelight, shooterLimelight, catapult, elevator);
  private final Command fourBallLimelight = new FourBallLimelight(drivetrain, intakeLimelight, shooterLimelight, catapult, elevator);

  // A chooser for autonomous commands
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  SendableChooser<Integer> liftChooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // // Configure default commands
    // Set the default drive command to split-stick arcade drive
    drivetrain.setDefaultCommand(
      // A split-stick arcade command, with forward/backward controlled by the left
      // hand, and turning controlled by the right.
      new DefaultDrive(
          drivetrain,
          () -> -driverController.getLeftY(),
          () -> driverController.getLeftX(),
          () -> driverController.getRightX()
          )
    );

    // Add commands to the autonomous command chooser
    autoChooser.addOption("Four Ball", fourBallLimelight);
    autoChooser.setDefaultOption("Two Ball", twoBallLimelight);

    // Add selections for lift
    liftChooser.addOption("Manual", 0);
    liftChooser.addOption("Low Bar", 1);
    liftChooser.setDefaultOption("High Bar", 2);

    // Put the chooser on the dashboard
    autoTab.addSendable(autoChooser);
    teleTab.addSendable(liftChooser);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@linkXboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Tracking Shooter Control
    //new JoystickButton(driverController, Button.kA.value).whenHeld(new TrackShooter(drivetrain, shooterLimelight).andThen(new LoadCatapult(elevator, catapult).andThen(new AutoShoot(catapult))));
    new JoystickButton(driverController, Button.kA.value).whenHeld(new LED(led, Constants.Colors.kDarkRed).andThen(new TrackShooter(drivetrain, shooterLimelight)).andThen(new LED(led, Constants.Colors.kDarkGreen)));

    // Elevator and Intake Up
    new JoystickButton(operatorController, Button.kY.value).whenHeld(new BallControl(elevator, true));

    // Elevator and Intake Down
    new JoystickButton(operatorController, Button.kX.value).whenHeld(new BallControl(elevator, false));

    //  Lift Up
    //new JoystickButton(operatorController, Button.kRightBumper.value).whenHeld(new LiftControl(lift, true, liftChooser.getSelected()));

    // Lift Down
    //new JoystickButton(operatorController, Button.kRightBumper.value).whenHeld(new LiftControl(lift, false, liftChooser.getSelected()));


    // SW Down
    new JoystickButton(operatorController, Button.kRightBumper.value).whenPressed(new SecretControl(secretWeapon, true));

    // SW Up
    new JoystickButton(operatorController, Button.kLeftBumper.value).whenPressed(new SecretControl(secretWeapon, false));

    // Load and Shoot Test
    new JoystickButton(operatorController, Button.kA.value).whenHeld(new LoadCatapult(elevator, catapult));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    SmartDashboard.putString("Selected: ", (autoChooser.getSelected().toString()));
    return autoChooser.getSelected();
  }

  public void updateTelemetry() {
    SmartDashboard.putNumber("Gyro Angle", drivetrain.getAngle());
    SmartDashboard.putNumber("Prox", elevator.getProx());
    SmartDashboard.putBoolean("Catapult has Ball", catapult.hasBall());
    SmartDashboard.putBoolean("Elevator has Ball", elevator.hasBall());
  }
}

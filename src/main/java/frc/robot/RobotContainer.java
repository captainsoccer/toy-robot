// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.Arm.controlMode;
import frc.robot.subsystems.drivetrain.Drivebase;
import frc.util.humanIO.CommandPS5Controller;
import frc.util.humanIO.JoystickAxis;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Arm arm;
  public static Drivebase drivebase;
  // Replace with CommandPS4Controller or CommandJoystick if needed
  static CommandPS5Controller driveController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    driveController = new CommandPS5Controller(0);
    arm = new Arm();
    drivebase = Drivebase.getInstance();
    // drivebase.resetGyro();
    // drivebase.drive(0, 0, 0, null);
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    driveController.cross().onTrue(new InstantCommand(() -> drivebase.resetGyro()));
    driveController.triangle().onTrue(new InstantCommand(() -> {
      switch(arm.mode){
        case ROTATION:
          arm.mode = controlMode.EXTENSTION;
          arm.disableArmRotaion();
          System.out.println("now extenstion");
          break;

        case EXTENSTION:
          arm.mode = controlMode.ROTATION;
          arm.disableArmExtenstion();
          System.out.println("now rotation");
          break;
      }
    }));
  }

  public static double calculateDeadband(double value) {
    if (Math.abs(value) < 0.2) return 0;
    return value;
  }

  public static CommandPS5Controller getDriveController(){
    return driveController;
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}

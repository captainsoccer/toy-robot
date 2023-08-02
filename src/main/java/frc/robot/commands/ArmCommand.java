// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.arm.Arm;
import frc.util.humanIO.JoystickAxis;

public class ArmCommand extends CommandBase {
  Arm arm = RobotContainer.arm;
  /** Creates a new ArmCommand. */
  public ArmCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double up = (RobotContainer.getDriveController().getRawAxis(4)/2) + 0.5;
    double down = (RobotContainer.getDriveController().getRawAxis(3)/2) + 0.5;
    double speed;

    up = RobotContainer.calculateDeadband(up) * 0.3;
    down = RobotContainer.calculateDeadband(down) * 0.3;

    if(up > 0 && down == 0){
      speed = up;
    }
    else if(down > 0 && up == 0){
      speed = -down;
    }
    else{
      speed = 0;
    }

    switch(arm.mode){
      case ROTATION:
        arm.rotateArm(speed);
        break;

      case EXTENSTION:
        arm.extendArm(speed);
        break;

      default:
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

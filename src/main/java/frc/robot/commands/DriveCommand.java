package frc.robot.commands;

import java.sql.Time;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.Drivetrain;
import frc.robot.Constants.Drivetrain.SwerveModuleConstants;
import frc.robot.subsystems.drivetrain.Drivebase;
import frc.util.humanIO.CommandPS5Controller;

public class DriveCommand extends CommandBase {
  Drivebase swerve;
  private double xSpeed;
  private double ySpeed;
  private double rotation;
  private double previusAngle;
  private double lastchangeTime;
  CommandPS5Controller driveController;
  public DriveCommand() {
    swerve = Drivebase.getInstance();
    driveController = RobotContainer.getDriveController();
    addRequirements(swerve);
  }

  @Override
  public void initialize() {
    rotation = 0;
    xSpeed = 0;
    ySpeed = 0;
    previusAngle = swerve.getAngle();
    lastchangeTime = Timer.getFPGATimestamp();
  }

  // public void execute() {
  //   xSpeed = RobotContainer.calculateDeadband(driveController.getRawAxis(0))
  //       * SwerveModuleConstants.freeSpeedMetersPerSecond;
  //   ySpeed = -RobotContainer.calculateDeadband(driveController.getRawAxis(1))
  //       * SwerveModuleConstants.freeSpeedMetersPerSecond;
  //   if (RobotContainer.calculateDeadband(driveController.getRawAxis(2)) != 0) {
  //     rotation = RobotContainer.calculateDeadband(driveController.getRawAxis(2)) * 10;
  //   } else rotation = swerve.getRotationPID(swerve.getAngle());
  //   swerve.drive(xSpeed ,ySpeed , rotation);
  // }
  @Override
  public void execute() {
    ySpeed = RobotContainer.calculateDeadband(driveController.getRawAxis(0))
      * SwerveModuleConstants.freeSpeedMetersPerSecond;
    xSpeed = -RobotContainer.calculateDeadband(driveController.getRawAxis(1))
      * SwerveModuleConstants.freeSpeedMetersPerSecond;
    if (RobotContainer.calculateDeadband(driveController.getRawAxis(2)) != 0) {
      rotation = RobotContainer.calculateDeadband(driveController.getRawAxis(2)) * 10;
      swerve.drive(ySpeed, xSpeed, rotation, Rotation2d.fromDegrees(swerve.getAngleDegrees()));
      lastchangeTime = Timer.getFPGATimestamp();
    } else{
      if (previusAngle - swerve.getAngle() <= 5 || Timer.getFPGATimestamp() - lastchangeTime > 5){
        swerve.resetAimedRotationToCurrentRotation();
      }
      else{
        previusAngle = swerve.getAngle();
      }
      rotation = swerve.calculateChassisPID();
      swerve.drive(ySpeed, xSpeed, rotation, swerve.getDesiredAngle());
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerve.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

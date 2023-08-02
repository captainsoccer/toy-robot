// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.util.*;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final double fullThres = 0.99;
  public static final double heldThres = -0.9;

  public static final class ArmConstants{
    public final static int roationMotorID = 28;
    public final static int extentionMotorID = 24;
  }

  public static final class Drivetrain {
    /**
     * The constants for the Swerve module.
     * 
     * @param idDrive            The ID of the drive motor.
     * @param driveGains         The drive gains.
     * @param idSteering         The ID of the steering motor.
     * @param steeringGains      The steering gains.
     * @param cancoderZeroAngle  The zero angle of the CANCoder.
     * @param canCoderId         The CANCoder ID.
     * @param isSteeringInverted Whether the steering is inverted.
     * @param isDriveInverted    Whether the drive is inverted.
     */
    public static class SwerveModuleConstants {
      public static double freeSpeedMetersPerSecond = 4.75;
      public static final double driveRatio = 6.75;
      public static final double steeringRatio = 12.5;
      public static final double wheelRadiusMeters = 0.0508; // 2 inches (in meters)
      public static final double wheelCircumferenceMeters = wheelRadiusMeters * 2 * Math.PI;
      public static final double driveDPRMeters = wheelCircumferenceMeters * driveRatio;
      public static final double steeringPositionConversionFactor = 1 / steeringRatio * 360; // degrees / rotation
      public static final double steeringVelocityConversionFactor = steeringPositionConversionFactor / 60; // degrees /
                                                                                                           // (rotations
                                                                                                           // *
      //                                                                                                      // seconds/minute)
      // public final static double cancoderTLOffset = 0;
      // public final static double cancoderTROffset = 0;
      // public final static double cancoderBLOffset = 0;
      // public final static double cancoderBROffset = 0;

      
      // CANCoder angle offset
      // Used to find a common zeroed out position for all modules
      public final static double cancoderTLOffset = 307;
      public final static double cancoderTROffset = 324.931640625;
      public final static double cancoderBLOffset = 59.4140625;
      public final static double cancoderBROffset = 179.2;


      public final int idDrive;
      public final PIDFGains driveGains;
      public final int idSteering;
      public final PIDFGains steeringGains;
      public final double cancoderZeroAngle;
      public final int canCoderId;
      public final boolean isSteeringInverted;
      public final boolean isDriveInverted;

      public SwerveModuleConstants(int idDrive, int idSteering, double cancoderZeroAngle,
          int canCoderId, boolean isSteeringInverted, boolean isDriveInverted) {
        this(idDrive, idSteering, new PIDFGains(0.05, 0.000, 0.00, 0.05, 25, 0),
            new PIDFGains(0.1, 0, 0.1, 0, 0.75, 0), cancoderZeroAngle, canCoderId, isSteeringInverted, isDriveInverted);
      }

      public SwerveModuleConstants(int idDrive, int idSteering, PIDFGains driveGains,
          PIDFGains steeringGains, double cancoderZeroAngle, int canCoderId, boolean isSteeringInverted, boolean isDriveInverted) {
        this.idDrive = idDrive;
        this.driveGains = driveGains;
        this.idSteering = idSteering;
        this.steeringGains = steeringGains;
        this.cancoderZeroAngle = cancoderZeroAngle;
        this.canCoderId = canCoderId;
        this.isSteeringInverted = isSteeringInverted;
        this.isDriveInverted = isDriveInverted;
      }
    }

    public static final SwerveModuleConstants FLModule = new SwerveModuleConstants(2, 3,
        SwerveModuleConstants.cancoderTLOffset, 10, false, true );
    public static final SwerveModuleConstants FRModule = new SwerveModuleConstants(4, 5,
        SwerveModuleConstants.cancoderTROffset, 11, false, false);
    public static final SwerveModuleConstants BLModule = new SwerveModuleConstants(6,  7,
        SwerveModuleConstants.cancoderBLOffset, 12, false, true);
    public static final SwerveModuleConstants BRModule = new SwerveModuleConstants(8, 9,
        SwerveModuleConstants.cancoderBROffset, 13, false, false);
  
    public static final PIDFGains xAutoPID = new PIDFGains(0.1, 0.0, 0.0);
    public static final PIDFGains yAutoPID = new PIDFGains(0.1, 0.0, 0.0);
    public static final PIDFGains angleAutoPID = new PIDFGains(0.6, 0.0, 0.0);
    public static final PIDFGains thetaPIDController = new PIDFGains(0.05, 0, 0.005, 0, 0.01, 0);
    public static final double kTrackWidth = 0.55; // Distance between right and left wheels
    public static final double kWheelBase = 0.55; // Distance between front and back wheels
    public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

      
  }
}

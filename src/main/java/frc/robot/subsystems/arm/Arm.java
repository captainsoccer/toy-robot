// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.arm;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  TalonFX m_rotaionMotor;
  TalonFX m_extentionMotor;
  public controlMode mode;
  public Arm() {
    m_rotaionMotor = new TalonFX(ArmConstants.roationMotorID);
    m_extentionMotor = new TalonFX(ArmConstants.extentionMotorID);
    mode = controlMode.ROTATION;
  }

  public enum controlMode{
    ROTATION,
    EXTENSTION
  }

  public void rotateArm(double speed){
    speed = MathUtil.clamp(speed, -0.9, 0.9);
    m_rotaionMotor.set(ControlMode.PercentOutput, speed);
  }

  public void extendArm(double speed){
    speed = MathUtil.clamp(speed, -0.9, 0.9);
    m_extentionMotor.set(ControlMode.PercentOutput, speed);
  }

  public void disableArmRotaion(){
    m_rotaionMotor.set(ControlMode.Disabled, 0);
  }

  public void disableArmExtenstion(){
    m_extentionMotor.set(ControlMode.Disabled, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

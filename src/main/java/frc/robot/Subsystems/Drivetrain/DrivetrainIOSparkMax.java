// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotorConstants;

public class DrivetrainIOSparkMax implements DrivetrainIO {
    private final SparkMax leftMain = new SparkMax(MotorConstants.driveLeftMain, MotorType.kBrushless);
    private final SparkMax leftFollow = new SparkMax(MotorConstants.driveLeftFollow, MotorType.kBrushless);
    private final RelativeEncoder leftEncoder = leftMain.getEncoder();

    private final SparkMax rightMain = new SparkMax(MotorConstants.driveRightMain, MotorType.kBrushless);
    private final SparkMax rightFollow = new SparkMax(MotorConstants.driveRightFollow, MotorType.kBrushless);
    private final RelativeEncoder rightEncoder = rightMain.getEncoder();

    private boolean isClosedLoop = false;
    private final SparkClosedLoopController leftPID = leftMain.getClosedLoopController();
    private final SparkClosedLoopController rightPID = rightMain.getClosedLoopController();    

    public DrivetrainIOSparkMax() {
        //leftMain.restoreFactoryDefaults(); //I guess I just get rid of this??
        //leftFollow.restoreFactoryDefaults();
        //rightMain.restoreFactoryDefaults();
        //rightFollow.restoreFactoryDefaults();

        leftMain.setCANTimeout(250);
        leftFollow.setCANTimeout(250);
        rightMain.setCANTimeout(250);
        rightFollow.setCANTimeout(250);

        leftMain.setInverted(true);
        rightMain.setInverted(false);

        leftFollow.follow(leftMain);
        rightFollow.follow(rightMain);

        leftMain.enableVoltageCompensation(12.0);
        rightMain.enableVoltageCompensation(12.0);

        leftMain.setSmartCurrentLimit(40);
        rightMain.setSmartCurrentLimit(40);

        leftPID.setP(DriveConstants.kPReal);
        leftPID.setD(DriveConstants.kDReal);
        rightPID.setP(DriveConstants.kPReal);
        rightPID.setD(DriveConstants.kDReal);

        leftMain.burnFlash();
        leftFollow.burnFlash();
        rightMain.burnFlash();
        rightFollow.burnFlash();
        
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftRotationsRad = Units.rotationsToRadians(leftEncoder.getPosition() / DriveConstants.gearRatio);
        inputs.leftPositionMeters = (leftEncoder.getPosition() / DriveConstants.gearRatio) * 2 * Math.PI * DriveConstants.wheelRadius;
        inputs.leftVelocityRadPerSec = Units.rotationsPerMinuteToRadiansPerSecond(leftEncoder.getVelocity() / DriveConstants.gearRatio);
        inputs.leftVelocityMetersPerSecond = ((leftEncoder.getVelocity() / DriveConstants.gearRatio) / 60) * 2 * Math.PI * DriveConstants.gearRatio;
        inputs.leftAppliedVolts = leftMain.getBusVoltage() * leftMain.getAppliedOutput();
        inputs.leftCurrentAmps = new double[] {leftMain.getOutputCurrent(), leftFollow.getOutputCurrent()};
        inputs.leftTempCelsius = new double[] {leftMain.getMotorTemperature(), leftFollow.getMotorTemperature()};

        inputs.rightRotationsRad = Units.rotationsToRadians(rightEncoder.getPosition() / DriveConstants.gearRatio);
        inputs.rightPositionMeters = (rightEncoder.getPosition() / DriveConstants.gearRatio) * 2 * Math.PI * DriveConstants.wheelRadius;
        inputs.rightVelocityRadPerSec = Units.rotationsPerMinuteToRadiansPerSecond(rightEncoder.getVelocity() / DriveConstants.gearRatio);
        inputs.rightVelocityMetersPerSecond = ((rightEncoder.getVelocity() / DriveConstants.gearRatio) / 60) * 2 * Math.PI * DriveConstants.gearRatio;
        inputs.rightAppliedVolts = rightMain.getBusVoltage() * rightMain.getAppliedOutput();
        inputs.rightCurrentAmps = new double[] {rightMain.getOutputCurrent(), rightFollow.getOutputCurrent()};
        inputs.rightTempCelsius = new double[] {rightMain.getMotorTemperature(), rightFollow.getMotorTemperature()};

        inputs.isClosedLoop = isClosedLoop;
    }

    @Override
    public void setVolts(double left, double right) {
        leftMain.setVoltage(left);
        rightMain.setVoltage(right);
    }

    @Override
    public void setMetersPerSecond(double left, double right) {
        leftPID.setReference(Units.radiansPerSecondToRotationsPerMinute(left * DriveConstants.gearRatio), ControlType.kVelocity, 0, DriveConstants.kV);
        rightPID.setReference(Units.radiansPerSecondToRotationsPerMinute(right * DriveConstants.gearRatio), ControlType.kVelocity, 0, DriveConstants.kV);
    }
}
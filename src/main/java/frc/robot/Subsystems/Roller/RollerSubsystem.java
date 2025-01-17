// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Roller;

import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.RollerConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class RollerSubsystem extends SubsystemBase {
    private final SparkMax rollermotor = new SparkMax(MotorConstants.rollerID, MotorType.kBrushed);

    public RollerSubsystem() {
        rollermotor.setCANTimeout(250);

        SparkMaxConfig rollerConfig = new SparkMaxConfig();
        rollerConfig.voltageCompensation(RollerConstants.ROLLER_MOTOR_VOLTAGE_COMP);
        rollerConfig.smartCurrentLimit(RollerConstants.ROLLER_MOTOR_CURRENT_LIMIT);
        rollermotor.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {}

    public Command runRoller(RollerSubsystem rollerSubsystem, DoubleSupplier forward, DoubleSupplier reverse) {
        return Commands.run(
        () -> rollermotor.set(forward.getAsDouble() - reverse.getAsDouble()), rollerSubsystem);
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Roller;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.MotorConstants;

/** Add your docs here. */
public class RollerSparkMax implements RollerIO {
    private final SparkMax rollermotor = new SparkMax(MotorConstants.rollerID, MotorType.kBrushless);

    public RollerSparkMax() {
        rollermotor.setCANTimeout(250);
    }

    @Override
    public void updateInputs(RollerIOInputs inputs) {
        
    }

    @Override
    public void setVolts(double speed) {
        rollermotor.set(speed);
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.roller;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.MotorConstants;

public class RollerSparkMax implements RollerIO {
    //the motor for the roller on th top
    private final SparkMax rollermotor = new SparkMax(MotorConstants.RollerID, MotorType.kBrushless);

    public RollerSparkMax() {
        rollermotor.setCANTimeout(250); //sets the timeout for the motor
    }

    //sets the voltages for the motor
    @Override
    public void setVolts(double speed) {
        rollermotor.set(speed);
    }
}

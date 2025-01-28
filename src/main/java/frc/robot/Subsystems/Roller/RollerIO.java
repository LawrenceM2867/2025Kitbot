// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Roller;

import org.littletonrobotics.junction.AutoLog;

import frc.robot.Subsystems.Drivetrain.DrivetrainIO.DrivetrainIOInputs;

/** Add your docs here. */
public interface RollerIO {
    @AutoLog
    public static class RollerIOInputs {
        
    }

    public abstract void updateInputs(DrivetrainIOInputs inputs);

    public abstract void setVolts(double left, double right);
}

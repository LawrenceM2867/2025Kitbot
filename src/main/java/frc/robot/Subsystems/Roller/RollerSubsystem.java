// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Roller;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Roller.RollerIO.RollerIOInputs;

/** Add your docs here. */
public class RollerSubsystem extends SubsystemBase {
    RollerIO io;
    RollerIOInputs inputs = new RollerIOInputs();

    public RollerSubsystem(RollerIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
    }

    public Command runRoller(DoubleSupplier speed) {
        return new RunCommand(() -> this.setVoltages(speed.getAsDouble()), this);
    }
    
    private void setVoltages(double speed) {
        io.setVolts(speed);
      }
}

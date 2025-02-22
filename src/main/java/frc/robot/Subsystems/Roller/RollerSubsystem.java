// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Roller;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Roller.RollerIO.RollerIOInputs;

public class RollerSubsystem extends SubsystemBase {
    RollerIO io; //the IO interface
    RollerIOInputs inputs = new RollerIOInputs(); //the inputs used for the drivetrain

    //sets the roller to the sparkmax io
    public RollerSubsystem(RollerIO io) {
        this.io = io;
    }

    //the command to spin the roller (uses setVoltages())
    public Command runRoller(DoubleSupplier speed) {
        return new RunCommand(() -> this.setVoltages(speed.getAsDouble()), this);
    }
    
    //sets the voltages for the motor, used by the command runRoller()
    private void setVoltages(double speed) {
        io.setVolts(speed);
    }
}

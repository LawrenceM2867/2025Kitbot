// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Drivetrain.DrivetrainIO.DrivetrainIOInputs;

public class DrivetrainSubsystem extends SubsystemBase {
  DrivetrainIO io; //the IO interface
  DrivetrainIOInputs inputs = new DrivetrainIOInputs(); //the inputs used for the drivetrain
  //make a new odometry and kineamtics for updating the robot's logging
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0);
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.5);

  //sets the volts of either the real or simulated motors
  private void setVoltages(double left, double right) {
    io.setVolts(left, right);
  }

  //the command to set the voltages (uses setVoltages())
  public Command setVoltagesCommand(DoubleSupplier left, DoubleSupplier right) {
    return new RunCommand(() -> this.setVoltages(left.getAsDouble(), right.getAsDouble()), this);
  }

  //the arcade command that takes inputs from the controller to move the robot with moving foward/backwards and rotating left and right
  public Command setVoltagesArcadeCommand(DoubleSupplier speed, DoubleSupplier rotate) {
    return new RunCommand(() -> {
      var speeds = DifferentialDrive.arcadeDriveIK(speed.getAsDouble(), rotate.getAsDouble(), false); //makes the differential drive calculations
      this.setVoltages(speeds.left, speeds.right); //sets the voltages based on the calculations
    }, this);
  }

  //sets the drivetrain to either real or sim
  public DrivetrainSubsystem(DrivetrainIO io) {
    this.io = io;
  }

  //periodically updates inputs, the odometry, and records the outputs
  @Override
  public void periodic() {
    io.updateInputs(inputs);
    odometry.update( //just a bunch of math stuff
    odometry.getPoseMeters().getRotation()
        .plus(Rotation2d.fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
            * 0.020 / Units.inchesToMeters(26))),
    inputs.leftPositionMeters, inputs.rightPositionMeters);
    Logger.recordOutput("Drivebase Pose", odometry.getPoseMeters());
  }
}
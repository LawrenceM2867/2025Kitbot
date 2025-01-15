// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Subsystems.Drivetrain.DrivetrainIOSim;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;

public class RobotContainer {
  // Create a new Xbox controller on port 0
  CommandXboxController controller = new CommandXboxController(0);

  DrivetrainSubsystem drivetrainSubsystem;

  public RobotContainer() {
    drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainIOSim());
    configureBindings();
  }

  private void configureBindings() {
    drivetrainSubsystem.setDefaultCommand(
      drivetrainSubsystem.driveCommand(
        () -> modifyJoystick(-controller.getLeftY()),
        () -> modifyJoystick(controller.getLeftX()),
        () -> DriveConstants.isClosedLoop));
  }

  private double modifyJoystick(double in) {
    if (Math.abs(in) < 0.05) {
      return 0;
    }
    return in * in * Math.signum(in);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

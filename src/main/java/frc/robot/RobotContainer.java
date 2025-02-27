// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;
import frc.robot.subsystems.drivetrain.DrivetrainTalonSRX;
import frc.robot.subsystems.roller.RollerSparkMax;
import frc.robot.subsystems.roller.RollerSubsystem;

public class RobotContainer {
  //the controller being used to control the robot
  CommandXboxController controller = new CommandXboxController(0);

  //the subsystems that will be used
  DrivetrainSubsystem drivetrainSubsystem;
  RollerSubsystem rollerSubsystem;

  public RobotContainer() {
    //sets the subsystems, you can change the drivetrain one to DrivetrainIOSim() for it to work on the simulator
    drivetrainSubsystem = new DrivetrainSubsystem(new DrivetrainTalonSRX());
    rollerSubsystem = new RollerSubsystem(new RollerSparkMax());
    configureBindings(); //configures the bindings
  }

  private void configureBindings() {
    //controls the differential drivetrain with the left joystick (up/down to move and left/right to turn)
    drivetrainSubsystem.setDefaultCommand(
      drivetrainSubsystem.setVoltagesArcadeCommand(
        () -> -modifyJoystick(controller.getLeftY()) / 2,
        () -> modifyJoystick(controller.getLeftX()) / 2));

    //controls the roller with the right joystick (up/down to move it foward and backwards :)
    rollerSubsystem.setDefaultCommand(
      rollerSubsystem.runRoller(
        () -> MathUtil.applyDeadband(controller.getRightY(), 0.1) / 4));
  }
  
  //more math to add a lerp effect to the joystick (also cuts it off if it's too low)
  private double modifyJoystick(double in) {
    if (Math.abs(in) < 0.05) {
      return 0;
    }
    return in * in * Math.signum(in);
  }

  //automonous commands, were not used
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

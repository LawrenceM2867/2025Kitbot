// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import frc.robot.Constants.SimConstants;

public class DrivetrainIOSim implements DrivetrainIO {
    //create the motors
    TalonFX leftFalcon = new TalonFX(SimConstants.DrivetrainLeftFalconID);
    TalonFX rightFalcon = new TalonFX(SimConstants.DrivetrainRightFalconID);
    
    //create voltages to set the motors to
    VoltageOut leftVoltage = new VoltageOut(0);
    VoltageOut rightVoltage = new VoltageOut(0);

    //create the simulation
    DifferentialDrivetrainSim physicsSim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDoubleFalcon500PerSide,
    KitbotGearing.k8p45,
    KitbotWheelSize.kSixInch,
    null);

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        physicsSim.update(0.020); //update the simulator

        //update all of the inputs for the simulator
        var leftSimState = leftFalcon.getSimState();
        leftSimState.setSupplyVoltage(RoboRioSim.getVInVoltage());

        var rightSimState = rightFalcon.getSimState();
        rightSimState.setSupplyVoltage(RoboRioSim.getVInVoltage());

        physicsSim.setInputs(leftSimState.getMotorVoltage(), rightSimState.getMotorVoltage());

        inputs.leftOutputVolts = leftSimState.getMotorVoltage();
        inputs.rightOutputVolts = rightSimState.getMotorVoltage();

        inputs.leftVelocityMetersPerSecond = physicsSim.getLeftVelocityMetersPerSecond();;
        inputs.rightVelocityMetersPerSecond = physicsSim.getRightVelocityMetersPerSecond();;

        inputs.leftPositionMeters = physicsSim.getLeftPositionMeters();
        inputs.rightPositionMeters = physicsSim.getRightPositionMeters();

        inputs.leftCurrentAmps = new double[] {leftSimState.getTorqueCurrent()};
        inputs.rightCurrentAmps = new double[] {rightSimState.getTorqueCurrent()};

        inputs.leftTempCelsius = new double[0];
        inputs.rightTempCelsius = new double[0];
    }

    @Override
    public void setVolts(double left, double right) {
        //set the volts of the simulated motors
        leftFalcon.setControl(leftVoltage.withOutput(left));
        rightFalcon.setControl(rightVoltage.withOutput(right));        
    }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants.MotorConstants;

/** Add your docs here. */
public class DrivetrainTalonSRX implements DrivetrainIO {

    TalonSRX tLF = new TalonSRX(MotorConstants.LFTalonID);
    TalonSRX tLB = new TalonSRX(MotorConstants.LBTalonID);
    TalonSRX tRF = new TalonSRX(MotorConstants.RFTalonID);
    TalonSRX tRB = new TalonSRX(MotorConstants.RBTalonID);

    public DrivetrainTalonSRX() {
        tLF.setNeutralMode(NeutralMode.Coast);
        tRF.setNeutralMode(NeutralMode.Coast);

        tLF.setInverted(true);
        tLB.setInverted(true);

        tLB.follow(tLF);
        tRB.follow(tRF);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = tLF.getMotorOutputVoltage();
        inputs.leftTempCelsius = new double[] {(tLF.getTemperature())};
        inputs.leftCurrentAmps = new double[] {(tLF.getSupplyCurrent())};

        inputs.rightOutputVolts = tRF.getMotorOutputVoltage();
        inputs.rightTempCelsius = new double[] {(tRF.getTemperature())};
        inputs.rightCurrentAmps = new double[] {(tRF.getSupplyCurrent())};
    }

    @Override
    public void setVolts(double left, double right) {
        tLF.set(TalonSRXControlMode.PercentOutput, left);
        tRF.set(TalonSRXControlMode.PercentOutput, right);
    }

}
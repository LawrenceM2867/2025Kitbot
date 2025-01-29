// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Constants.MotorConstants;

/** Add your docs here. */
public class DrivetrainTalonSRX implements DrivetrainIO {

    TalonSRX TLF = new TalonSRX(MotorConstants.LFTalonID);
    TalonSRX TLB = new TalonSRX(MotorConstants.LBTalonID);
    TalonSRX TRF = new TalonSRX(MotorConstants.RFTalonID);
    TalonSRX TRB = new TalonSRX(MotorConstants.LFTalonID);

    public DrivetrainTalonSRX() {
        TLB.follow(TLF);
        TRB.follow(TRF);

        TLF.setNeutralMode(NeutralMode.Coast);
        TRF.setNeutralMode(NeutralMode.Coast);

        TLF.setInverted(true);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = TLF.getMotorOutputVoltage();
        inputs.leftTempCelsius = new double[] {(TLF.getTemperature())};
        inputs.leftCurrentAmps = new double[] {(TLF.getSupplyCurrent())};

        inputs.rightOutputVolts = TRF.getMotorOutputVoltage();
        inputs.rightTempCelsius = new double[] {(TRF.getTemperature())};
        inputs.rightCurrentAmps = new double[] {(TRF.getSupplyCurrent())};
    }

    @Override
    public void setVolts(double left, double right) {
        TLF.set(TalonSRXControlMode.PercentOutput, left);
        TRF.set(TalonSRXControlMode.PercentOutput, right);
    }

}
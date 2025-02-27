// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Constants.MotorConstants;

public class DrivetrainTalonSRX implements DrivetrainIO {
    //creates all of the motors
    TalonSRX tLF = new TalonSRX(MotorConstants.LFTalonID);
    TalonSRX tLB = new TalonSRX(MotorConstants.LBTalonID);
    TalonSRX tRF = new TalonSRX(MotorConstants.RFTalonID);
    TalonSRX tRB = new TalonSRX(MotorConstants.RBTalonID);

    //initalization
    public DrivetrainTalonSRX() {
        //sets the motors to coast
        tLF.setNeutralMode(NeutralMode.Coast);
        tRF.setNeutralMode(NeutralMode.Coast);

        //inverts the left motors
        tLF.setInverted(true);
        tLB.setInverted(true);

        //makes the back motors follow the ones on the front
        tLB.follow(tLF);
        tRB.follow(tRF);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        //updates the inputs to log them
        inputs.leftOutputVolts = tLF.getMotorOutputVoltage();
        inputs.leftTempCelsius = new double[] {(tLF.getTemperature())};
        inputs.leftCurrentAmps = new double[] {(tLF.getSupplyCurrent())};

        inputs.rightOutputVolts = tRF.getMotorOutputVoltage();
        inputs.rightTempCelsius = new double[] {(tRF.getTemperature())};
        inputs.rightCurrentAmps = new double[] {(tRF.getSupplyCurrent())};
    }

    @Override
    public void setVolts(double left, double right) {
        //sets the voltages of the front motors, the back ones will always follow them
        tLF.set(TalonSRXControlMode.PercentOutput, left);
        tRF.set(TalonSRXControlMode.PercentOutput, right);
    }
}
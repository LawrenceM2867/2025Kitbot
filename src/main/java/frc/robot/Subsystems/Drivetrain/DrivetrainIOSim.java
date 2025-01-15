package frc.robot.Subsystems.Drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import frc.robot.Constants.DriveConstants;

public class DrivetrainIOSim implements DrivetrainIO {
    private double leftAppliedVolts = 0.0;
    private double rightAppliedVolts = 0.0;

    DifferentialDrivetrainSim sim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDoubleNEOPerSide,
    KitbotGearing.k8p45,
    KitbotWheelSize.kSixInch,
    null);

    private boolean isClosedLoop = false;
    private PIDController leftPID = new PIDController(DriveConstants.kPSim, DriveConstants.kISim, DriveConstants.kDSim);
    private PIDController rightPID = new PIDController(DriveConstants.kPSim, DriveConstants.kISim, DriveConstants.kDSim);

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        if (isClosedLoop) {
            leftAppliedVolts = MathUtil.clamp(leftPID.calculate(sim.getLeftVelocityMetersPerSecond() / DriveConstants.wheelRadius) + DriveConstants.kLeftFFVoltsSim, -12.0, 12.0);
            rightAppliedVolts = MathUtil.clamp(rightPID.calculate(sim.getRightVelocityMetersPerSecond() / DriveConstants.wheelRadius) + DriveConstants.kRightFFVoltsSim, -12.0, 12.0);
        }
        sim.update(0.020);

        sim.setInputs(leftAppliedVolts, rightAppliedVolts);

        inputs.leftRotationsRad = sim.getLeftPositionMeters() / DriveConstants.wheelRadius;
        inputs.leftPositionMeters = sim.getLeftPositionMeters();
        inputs.leftVelocityRadPerSec = sim.getLeftVelocityMetersPerSecond() / DriveConstants.wheelRadius;
        inputs.leftVelocityMetersPerSecond = sim.getLeftVelocityMetersPerSecond();
        inputs.leftVelocityGoalMetersPerSecond = (leftPID.getSetpoint());
        inputs.leftAppliedVolts = leftAppliedVolts;
        inputs.leftCurrentAmps = new double[] {sim.getLeftCurrentDrawAmps()};
        inputs.leftTempCelsius = new double[] {};

        inputs.rightRotationsRad = sim.getRightPositionMeters() / DriveConstants.wheelRadius;
        inputs.rightPositionMeters = sim.getRightPositionMeters();
        inputs.rightVelocityRadPerSec = sim.getRightVelocityMetersPerSecond() / DriveConstants.wheelRadius;
        inputs.rightVelocityMetersPerSecond = sim.getRightVelocityMetersPerSecond();
        inputs.rightVelocityGoalMetersPerSecond = (rightPID.getSetpoint());
        inputs.rightAppliedVolts = rightAppliedVolts;
        inputs.rightCurrentAmps = new double[] {sim.getRightCurrentDrawAmps()};
        inputs.rightTempCelsius = new double[] {};

        inputs.isClosedLoop = isClosedLoop;
        inputs.gyroYaw = sim.getHeading();
    }

    @Override
    public void setVolts(double left, double right) {
        leftAppliedVolts = -left;
        rightAppliedVolts = -right;
        isClosedLoop = false;
    }

    @Override
    public void setMetersPerSecond(double left, double right) {
        isClosedLoop = true;
        leftPID.setSetpoint(left);
        rightPID.setSetpoint(right);
    }
}

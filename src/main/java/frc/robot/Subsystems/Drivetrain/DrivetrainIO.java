package frc.robot.Subsystems.Drivetrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.DriveConstants;

public interface DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        public boolean isClosedLoop = DriveConstants.isClosedLoop;

        public double leftRotationsRad = 0.0;
        public double leftVelocityRadPerSec = 0.0;
        public double leftVelocityGoalMetersPerSecond = 0.0;

        public double rightRotationsRad = 0.0;
        public double rightVelocityRadPerSec = 0.0;
        public double rightVelocityGoalMetersPerSecond = 0.0;

        public double leftAppliedVolts = 0.0;
        public double rightAppliedVolts = 0.0;

        public double leftVelocityMetersPerSecond = 0.0;
        public double rightVelocityMetersPerSecond = 0.0;

        public double leftPositionMeters = 0.0;
        public double rightPositionMeters = 0.0;

        public double[] leftCurrentAmps = new double[0];
        public double[] leftTempCelsius = new double[0];
        public double[] rightCurrentAmps = new double[0];
        public double[] rightTempCelsius = new double[0];

        public Rotation2d gyroYaw = new Rotation2d();
    }

    public abstract void updateInputs(DrivetrainIOInputs inputs);

    public abstract void setVolts(double left, double right);

    public abstract void setMetersPerSecond(double left, double right);
}
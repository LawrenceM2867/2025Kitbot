package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

public interface DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        //inputs that are updated with updateInputs
        public double leftVelocityMetersPerSecond = 0.0;
        public double rightVelocityMetersPerSecond = 0.0;

        public double leftPositionMeters = 0.0;
        public double rightPositionMeters = 0.0;

        public double[] leftCurrentAmps = new double[0];
        public double[] leftTempCelsius = new double[0];
        public double[] rightCurrentAmps = new double[0];
        public double[] rightTempCelsius = new double[0];

        public double leftOutputVolts = 0.0;
        public double rightOutputVolts = 0.0;
    }

    public abstract void updateInputs(DrivetrainIOInputs inputs); //function to update the inputs for the motors

    public abstract void setVolts(double left, double right); //function to set volts for the motors
}
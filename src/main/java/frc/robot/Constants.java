// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public final class Constants {
    public static class DriveConstants {
        public static final double wheelRadius = Units.inchesToMeters(3.0);
        public static final double kLeftFFVoltsSim = 0.0;
        public static final double kRightFFVoltsSim = 0.0;

        public static boolean isClosedLoop = false;
        public static double trackWidth = Units.inchesToMeters(3.0);
        public static double gearRatio = 8.46;

        public static double kS = 0.0;
        public static double kV = 0.0;

        public static double kPSim = 0.2;
        public static double kISim = 0.0;
        public static double kDSim = 0.0;

        public static double kPReal = 0.2;
        public static double kIReal = 0.0;
        public static double kDReal = 0.0;
    }

    public static class RollerConstants {
        public static final int ROLLER_MOTOR_CURRENT_LIMIT = 60;
        public static final double ROLLER_MOTOR_VOLTAGE_COMP = 10;
        public static final double ROLLER_EJECT_VALUE = 0.44;
    }

    public static class MotorConstants {
        public static final int driveLeftMain = 1;
        public static final int driveLeftFollow = 2;

        public static final int driveRightMain = 1;
        public static final int driveRightFollow = 2;

        public static final int rollerID = 3;
    }
}

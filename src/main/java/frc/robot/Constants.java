// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public final class Constants {
    public static class RollerConstants {
        public static final int ROLLER_MOTOR_CURRENT_LIMIT = 60;
        public static final double ROLLER_MOTOR_VOLTAGE_COMP = 10;
        public static final double ROLLER_EJECT_VALUE = 0.44;
    }

    public static class MotorConstants {
        public static final int LBTalonID = 1;
        public static final int LFTalonID = 2;
        public static final int RFTalonID = 3;
        public static final int RBTalonID = 4;

        public static final int rollerID = 11;
    }

    public static class SimConstants {
        public static final int drivetrainLeftFalconID = 0;
        public static final int drivetrainRightFalconID = 1;
    }
}

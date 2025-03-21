// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public static class MotorConstants {
        //all of the real motor IDs for the robot, used to know which motor is being used
        public static final int LBTalonID = 3;
        public static final int LFTalonID = 1;
        public static final int RFTalonID = 2;
        public static final int RBTalonID = 4;

        public static final int RollerID = 11;
    }

    public static class SimConstants {
        //the simulation motor IDs
        public static final int DrivetrainLeftFalconID = 0;
        public static final int DrivetrainRightFalconID = 1;
    }
}

package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {

    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4); //TBD
        public static final double kDriveMotorGearRatio = 1 / 5.8462; //TBD
        public static final double kTurningMotorGearRatio = 1 / 18.0; //TBD
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters; //TBD
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI; //TBD
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60; //TBD
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60; //TBD
        public static final double kPTurning = 0.5; //TBD
    }

    public static final class DriveConstants {

        public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;
        

        public static final int kFrontLeftDriveCanID = 8;
        public static final int kBackLeftDriveCanID = 2;
        public static final int kFrontRightDriveCanID = 6;
        public static final int kBackRightDriveCanID = 4;

        public static final int kFrontLeftTurningCanID = 7;
        public static final int kBackLeftTurningCanID = 1;
        public static final int kFrontRightTurningCanID = 5;
        public static final int kBackRightTurningCanID = 3;

        public static final int kFrontLeftAbsoluteEncoderPort = 0; //TBD
        public static final int kBackLeftAbsoluteEncoderPort = 1; //TBD
        public static final int kFrontRightAbsoluteEncoderPort = 2; //TBD
        public static final int kBackRightAbsoluteEncoderPort = 3; //TBD

        public static final boolean kFrontLeftTurningEncoderReversed = true;
        public static final boolean kBackLeftTurningEncoderReversed = true;
        public static final boolean kFrontRightTurningEncoderReversed = true;
        public static final boolean kBackRightTurningEncoderReversed = true;

        public static final boolean kFrontLeftDriveEncoderReversed = true;
        public static final boolean kBackLeftDriveEncoderReversed = true;
        public static final boolean kFrontRightDriveEncoderReversed = false;
        public static final boolean kBackRightDriveEncoderReversed = false;

        public static final boolean kFrontLeftAbsoluteEncoderReversed = true; //TBD
        public static final boolean kBackLeftAbsoluteEncoderReversed = true; //TBD
        public static final boolean kFrontRightAbsoluteEncoderReversed = false; //TBD
        public static final boolean kBackRightAbsoluteEncoderReversed = false; //TBD

        public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;

        public static final double kFrontLeftAbsoluteEncoderOffset = 0; 
        public static final double kBackLeftAbsoluteEncoderOffset = 0; 
        public static final double kFrontRightAbsoluteEncoderOffset = 0; 
        public static final double kBackRightAbsoluteEncoderOffset = 0; 
        
        // Distance between right and left wheels
        public static final double kTrackWidth = Units.inchesToMeters(21); //TBD
        // Distance between front and back wheels
        public static final double kWheelBase = Units.inchesToMeters(25.5); //TBD
       
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;
        public static final int kDriverFieldOrientedButtonIdx = 1;

        public static final double kDeadband = 0.1;
    }

}


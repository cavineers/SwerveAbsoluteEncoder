package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSubsystem;
import java.lang.Math;
import java.util.function.Supplier;
import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveCommand extends CommandBase {

    private final SwerveDriveSubsystem swerveSubsystem;

    private final Supplier<Double> xSpdFunction, ySpdFunction, turningSpdFunction;
    private final Supplier<Boolean> fieldOrientedFunction;
    private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;

    public SwerveCommand(SwerveDriveSubsystem swerveSubsystem,
            Supplier<Double> xSpdFunction, Supplier<Double> ySpdFunction, Supplier<Double> turningSpdFunction,
            Supplier<Boolean> fieldOrientedFunction) {
        this.swerveSubsystem = swerveSubsystem;
        this.xSpdFunction = xSpdFunction;
        this.ySpdFunction = ySpdFunction;
        this.turningSpdFunction = turningSpdFunction;
        this.fieldOrientedFunction = fieldOrientedFunction;
        this.xLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.yLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        this.turningLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        // Get real-time joystick inputs
        double xSpeed = xSpdFunction.get();
        double ySpeed = ySpdFunction.get();
        double turningSpeed = turningSpdFunction.get();

        // Apply deadband -- compensated for when the joystick value does not return to exactly zero
        xSpeed = Math.abs(xSpeed) > OIConstants.kDeadband ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > OIConstants.kDeadband ? ySpeed : 0.0;
        turningSpeed = Math.abs(turningSpeed) > OIConstants.kDeadband ? turningSpeed : 0.0;

        // Smooths driving for jerky joystick movement & eases acceleration
        xSpeed = xLimiter.calculate(xSpeed) * DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        turningSpeed = turningLimiter.calculate(turningSpeed)
            * DriveConstants.kTeleDriveMaxAngularSpeedRadiansPerSecond;

        // Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;
        if (fieldOrientedFunction.get()) {
            // Relative to field
            //chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            //        xSpeed, ySpeed, turningSpeed, swerveSubsystem.getRotation2d());

            chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);
        } else {
            // Relative to robot
            chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);
        }
        
        // Convert chassis speeds to individual module states
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);
        
        // Output each module states to wheels
        swerveSubsystem.setModuleStates(moduleStates);
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }







    /*public SwerveCommand(SwerveDriveSubsystem subsystem) {

        addRequirements(Robot.m_swerveDriveSubsystem);
    }


    private double applyDead(double value, double deadband) {
        if (Math.abs(value) < deadband) {
            return 0;
        } else {
            return value;
        }
    }
    @Override
    public void execute() {
        double forward = Robot.m_robotContainer.getJoystickRawAxis(1); // Get left stick Y axis

        forward = applyDead(forward, 0.2); // Apply deadband, smooths out imput
        forward = Math.copySign(Math.pow(forward, 2.0), forward); // Square the input (while preserving the sign) to increase fine control while permitting full power

        double strafe = Robot.m_robotContainer.getJoystickRawAxis(0); // Get left stick X axis

        strafe = applyDead(strafe, 0.2); // Apply deadband, smooths out imput
        strafe = Math.copySign(Math.pow(strafe, 2.0), strafe); // Square the input (while preserving the sign) to increase fine control while permitting full power

        double rotation = Robot.m_robotContainer.getJoystickRawAxis(4); // Get right stick X axis

        rotation = applyDead(rotation, 0.2); // Apply deadband, smooths out imput
        rotation = Math.copySign(Math.pow(rotation, 2.0), rotation); // Square the input (while preserving the sign) to increase fine control while permitting full power

        Robot.m_swerveDriveSubsystem.drive(new Translation2d(forward, strafe), rotation, true);
    } */
}

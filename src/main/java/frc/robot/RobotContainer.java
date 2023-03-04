package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.Constants.OIConstants;

import frc.robot.commands.SwerveCommand;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveHoming;

public class RobotContainer {

    private final SwerveDriveSubsystem swerveSubsystem;

    public final Command SwerveHoming;

    private final Joystick driverJoystick;

    public RobotContainer() {

        swerveSubsystem = new SwerveDriveSubsystem();
        SwerveHoming = new SwerveHoming(swerveSubsystem);

        driverJoystick = new Joystick(OIConstants.kDriverJoystickPort);

        SwerveHoming.schedule();

        swerveSubsystem.setDefaultCommand(new SwerveCommand(
            swerveSubsystem,
            () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
            () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
            () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
            () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

        configureButtonBindings();
    };

    private void configureButtonBindings() {
        //new JoystickButton(driverJoystick, 2).onTrue(null)(() -> swerveSubsystem.zeroHeading());

    }   

}

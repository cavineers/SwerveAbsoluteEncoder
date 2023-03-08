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
import frc.robot.Robot;

public class RobotContainer {

    private final SwerveDriveSubsystem swerveSubsystem;


    private final Joystick driverJoystick;
    private final JoystickButton button;

    public RobotContainer() {

        swerveSubsystem = new SwerveDriveSubsystem();

        driverJoystick = new Joystick(OIConstants.kDriverJoystickPort);
        button = new JoystickButton(driverJoystick, 4);
        

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
        button.onTrue(new SwerveHoming(swerveSubsystem));
    }   

}

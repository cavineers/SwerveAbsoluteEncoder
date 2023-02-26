package frc.robot;

import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SwerveCommand;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.TestCommand;

public class RobotContainer {

    private final SwerveDriveSubsystem swerveSubsystem = new SwerveDriveSubsystem();

    private final Joystick driverJoystick = new Joystick(OIConstants.kDriverJoystickPort);

    public RobotContainer() {

        swerveSubsystem.setDefaultCommand(new TestCommand(swerveSubsystem));

        /*swerveSubsystem.setDefaultCommand(new SwerveCommand(
            swerveSubsystem,
            () -> -driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
            () -> driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
            () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis),
            () -> !driverJoystick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

        configureButtonBindings(); */
    };

    private void configureButtonBindings() {
        //new JoystickButton(driverJoystick, 2).onTrue(null)(() -> swerveSubsystem.zeroHeading());

    }   

}

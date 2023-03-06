package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.SwerveDriveSubsystem;

public class SwitchFunction extends CommandBase {

    private final SwerveDriveSubsystem swerveSubsystem;

    public SwitchFunction(SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        swerveSubsystem.zeroHeading();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
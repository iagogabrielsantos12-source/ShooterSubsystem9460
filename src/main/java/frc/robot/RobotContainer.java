package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

    private final CommandXboxController m_driverController = new CommandXboxController(0);

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {

        m_driverController.rightTrigger(0.5).whileTrue(
            Commands.startEnd(
                m_shooterSubsystem::Shoot,
                m_shooterSubsystem::Stop,
                m_shooterSubsystem
            )
        );
    }

    public Command getAutonomousCommand() {
        return Commands.none();
    }
}
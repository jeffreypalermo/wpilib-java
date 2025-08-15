package frc.robot.commands;

import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;

/** Drive forward continuously at a given speed (ft/s) until externally ended. */
public class DriveForwardAtSpeed implements Command {
  private final Drivetrain drivetrain;
  private final double speedFtps;

  public DriveForwardAtSpeed(Drivetrain drivetrain, double speedFtps) {
    this.drivetrain = drivetrain;
    this.speedFtps = Math.max(0.0, speedFtps);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute(double dt) {
    double percent = speedFtps / DriveConstants.MAX_LINEAR_SPEED_FTPS;
    drivetrain.tank(percent, percent);
  }

  @Override
  public boolean isFinished() { return false; }

  @Override
  public void end() { drivetrain.stop(); }
}

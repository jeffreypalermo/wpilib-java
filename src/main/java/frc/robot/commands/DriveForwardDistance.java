package frc.robot.commands;

import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;

/** Drive forward a specified distance (feet) at a target speed (ft/s). */
public class DriveForwardDistance implements Command {
  private final Drivetrain drivetrain;
  private final double distanceFeet;
  private final double speedFtps;
  private double distanceTraveled;

  public DriveForwardDistance(Drivetrain drivetrain, double distanceFeet, double speedFtps) {
    this.drivetrain = drivetrain;
    this.distanceFeet = Math.max(0.0, distanceFeet);
    this.speedFtps = Math.max(0.0, speedFtps);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute(double dt) {
    double percent = speedFtps / DriveConstants.MAX_LINEAR_SPEED_FTPS;
    drivetrain.tank(percent, percent);
    distanceTraveled += speedFtps * dt;
  }

  @Override
  public boolean isFinished() {
    return distanceTraveled >= distanceFeet;
  }

  @Override
  public void end() {
    drivetrain.stop();
  }
}

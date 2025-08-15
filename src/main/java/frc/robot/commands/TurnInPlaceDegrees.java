package frc.robot.commands;

import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;

/** Spin in place by a specified number of degrees at a constant rate. */
public class TurnInPlaceDegrees implements Command {
  private final Drivetrain drivetrain;
  private final double degrees;
  private final double degPerSec;
  private double rotated;
  private final boolean clockwise;

  public TurnInPlaceDegrees(Drivetrain drivetrain, double degrees, double degPerSec) {
    this.drivetrain = drivetrain;
    this.degrees = Math.abs(degrees);
    this.degPerSec = Math.abs(degPerSec);
    this.clockwise = degrees < 0; // negative means right/clockwise
  }

  @Override
  public void initialize() {}

  @Override
  public void execute(double dt) {
    // Map deg/sec to differential outputs via a simple linear model
    double percent = Math.min(1.0, degPerSec / 360.0); // 360 deg/sec ~= full output (placeholder)
    double left = clockwise ? percent : -percent;
    double right = -left;
    drivetrain.tank(left, right);
    rotated += degPerSec * dt;
  }

  @Override
  public boolean isFinished() { return rotated >= degrees; }

  @Override
  public void end() { drivetrain.stop(); }
}

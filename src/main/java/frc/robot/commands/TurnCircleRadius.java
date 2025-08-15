package frc.robot.commands;

import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.math.CircleDifferentialKinematics;

/** Turn in a circle of a given radius (feet). Runs until externally ended or timed by a wrapper. */
public class TurnCircleRadius implements Command {
  private final Drivetrain drivetrain;
  private final double radiusFeet;
  private final double baseSpeed;

  public TurnCircleRadius(Drivetrain drivetrain, double radiusFeet, double baseSpeed) {
    this.drivetrain = drivetrain;
    this.radiusFeet = radiusFeet;
    this.baseSpeed = baseSpeed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute(double dt) {
    double[] lr = CircleDifferentialKinematics.computeSideOutputs(radiusFeet, DriveConstants.TRACK_WIDTH_FEET, baseSpeed);
    drivetrain.tank(lr[0], lr[1]);
  }

  @Override
  public boolean isFinished() { return false; }

  @Override
  public void end() { drivetrain.stop(); }
}

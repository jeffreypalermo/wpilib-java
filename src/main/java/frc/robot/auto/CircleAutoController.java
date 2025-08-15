package frc.robot.auto;

import frc.robot.math.CircleDifferentialKinematics;

/** Computes commands to drive in a circle. */
public class CircleAutoController {
  private final double radiusFeet;
  private final double trackWidthFeet;
  private final double baseSpeed;

  public CircleAutoController(double radiusFeet, double trackWidthFeet, double baseSpeed) {
    this.radiusFeet = radiusFeet;
    this.trackWidthFeet = trackWidthFeet;
    this.baseSpeed = baseSpeed;
  }

  /** Compute the [left, right] differential side outputs for the configured circle. */
  public double[] computeSideOutputs() {
    return CircleDifferentialKinematics.computeSideOutputs(radiusFeet, trackWidthFeet, baseSpeed);
  }
}

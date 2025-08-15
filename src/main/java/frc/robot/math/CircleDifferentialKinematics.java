package frc.robot.math;

/** Kinematics for driving a differential (left/right) drivetrain in a circle. */
public final class CircleDifferentialKinematics {
  private CircleDifferentialKinematics() {}

  /**
   * Compute [left, right] side outputs to follow a circle of a given radius (to robot center).
   * Outputs are scaled so the larger magnitude equals baseSpeed (0..1).
   */
  public static double[] computeSideOutputs(double radiusFeet, double trackWidthFeet, double baseSpeed) {
    radiusFeet = Math.max(0.01, radiusFeet);
    trackWidthFeet = Math.max(0.01, trackWidthFeet);
    baseSpeed = Math.max(0.0, Math.min(1.0, baseSpeed));

    double halfTrack = trackWidthFeet / 2.0;
    double vLeft = radiusFeet - halfTrack;
    double vRight = radiusFeet + halfTrack;

    double maxMag = Math.max(Math.abs(vLeft), Math.abs(vRight));
    if (maxMag < 1e-6) {
      return new double[] {0.0, 0.0};
    }
    double scale = baseSpeed / maxMag;
    double leftOut = clamp(vLeft * scale);
    double rightOut = clamp(vRight * scale);
    return new double[] {leftOut, rightOut};
  }

  private static double clamp(double v) {
    return Math.max(-1.0, Math.min(1.0, v));
  }
}

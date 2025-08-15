package frc.robot.constants;

public final class DriveConstants {
  private DriveConstants() {}

  // PWM ports
  public static final int LEFT_FRONT_PWM = 0;
  public static final int LEFT_REAR_PWM = 1;
  public static final int RIGHT_FRONT_PWM = 2;
  public static final int RIGHT_REAR_PWM = 3;

  // Geometry (feet)
  public static final double TRACK_WIDTH_FEET = 2.0; // ~24 inches
  public static final double DEFAULT_CIRCLE_RADIUS_FEET = 10.0;

  // Behavior
  public static final double BASE_SPEED = 0.4; // 0..1
}

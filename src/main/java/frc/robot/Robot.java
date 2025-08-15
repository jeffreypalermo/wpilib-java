package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  // PWM channels (assumed)
  private static final int LEFT_FRONT_PWM = 0;
  private static final int LEFT_REAR_PWM = 1;
  private static final int RIGHT_FRONT_PWM = 2;
  private static final int RIGHT_REAR_PWM = 3;

  // Drive geometry and behavior
  // Assumptions: ~24 inch track width for a 4-wheeled car-style skid steer
  private static final double TRACK_WIDTH_FEET = 2.0; // distance between left and right wheels
  private static final double CIRCLE_RADIUS_FEET = 10.0; // desired path radius (to robot center)
  private static final double BASE_SPEED = 0.4; // scale motor output 0..1

  private PWMVictorSPX leftFront;
  private PWMVictorSPX leftRear;
  private PWMVictorSPX rightFront;
  private PWMVictorSPX rightRear;
  private MotorControllerGroup leftGroup;
  private MotorControllerGroup rightGroup;
  private DifferentialDrive drive;

  @Override
  public void robotInit() {
    // Motor controllers
    leftFront = new PWMVictorSPX(LEFT_FRONT_PWM);
    leftRear = new PWMVictorSPX(LEFT_REAR_PWM);
    rightFront = new PWMVictorSPX(RIGHT_FRONT_PWM);
    rightRear = new PWMVictorSPX(RIGHT_REAR_PWM);

    leftGroup = new MotorControllerGroup(leftFront, leftRear);
    rightGroup = new MotorControllerGroup(rightFront, rightRear);

    // Typically the right side is inverted for differential drives
    rightGroup.setInverted(true);

    drive = new DifferentialDrive(leftGroup, rightGroup);
    drive.setSafetyEnabled(false); // we'll command continuously in periodic

    SmartDashboard.putString("Status", "Robot Initialized");
    SmartDashboard.putNumber("CircleRadiusFeet", CIRCLE_RADIUS_FEET);
    SmartDashboard.putNumber("TrackWidthFeet", TRACK_WIDTH_FEET);
    SmartDashboard.putNumber("BaseSpeed", BASE_SPEED);
  }

  @Override
  public void robotPeriodic() {
    // Periodic diagnostics
  }

  @Override
  public void autonomousInit() {
    SmartDashboard.putString("Auto", "Circle drive started");
  }

  @Override
  public void autonomousPeriodic() {
    // Optionally allow runtime tuning from dashboard
    double radiusFeet = SmartDashboard.getNumber("CircleRadiusFeet", CIRCLE_RADIUS_FEET);
    double trackFeet = SmartDashboard.getNumber("TrackWidthFeet", TRACK_WIDTH_FEET);
    double base = SmartDashboard.getNumber("BaseSpeed", BASE_SPEED);

    double[] lr = computeCircleTankOutputs(radiusFeet, trackFeet, base);
    SmartDashboard.putNumber("LeftOutput", lr[0]);
    SmartDashboard.putNumber("RightOutput", lr[1]);
    drive.tankDrive(lr[0], lr[1], false);
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Leave teleop empty (no driver inputs specified)
  }

  @Override
  public void disabledInit() {
    if (drive != null) {
      drive.tankDrive(0, 0);
    }
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  /**
   * Compute left/right tank outputs to follow a circle of a given radius.
   * For a differential drive with track width L, wheel linear speeds are:
   * v_l = ω (R - L/2), v_r = ω (R + L/2). We output percentages proportional
   * to these speeds and scale so the larger magnitude equals baseSpeed.
   */
  private static double[] computeCircleTankOutputs(double radiusFeet, double trackWidthFeet, double baseSpeed) {
    // Guard rails
    radiusFeet = Math.max(0.01, radiusFeet);
    trackWidthFeet = Math.max(0.01, trackWidthFeet);
    baseSpeed = Math.max(0.0, Math.min(1.0, baseSpeed));

    double halfTrack = trackWidthFeet / 2.0;
    double vLeft = radiusFeet - halfTrack;   // proportional to inner wheel speed
    double vRight = radiusFeet + halfTrack;  // proportional to outer wheel speed

    // Normalize so the larger magnitude becomes baseSpeed
    double maxMag = Math.max(Math.abs(vLeft), Math.abs(vRight));
    if (maxMag < 1e-6) {
      return new double[] {0.0, 0.0};
    }
    double scale = baseSpeed / maxMag;
    double leftOut = vLeft * scale;
    double rightOut = vRight * scale;

    // Clamp to [-1, 1]
    leftOut = Math.max(-1.0, Math.min(1.0, leftOut));
    rightOut = Math.max(-1.0, Math.min(1.0, rightOut));
    return new double[] {leftOut, rightOut};
  }
}

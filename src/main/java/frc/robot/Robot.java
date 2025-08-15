package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.CircleAutoController;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
  private Drivetrain drivetrain;
  private CircleAutoController autoController;

  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();

    SmartDashboard.putString("Status", "Robot Initialized");
    SmartDashboard.putNumber("CircleRadiusFeet", DriveConstants.DEFAULT_CIRCLE_RADIUS_FEET);
    SmartDashboard.putNumber("TrackWidthFeet", DriveConstants.TRACK_WIDTH_FEET);
    SmartDashboard.putNumber("BaseSpeed", DriveConstants.BASE_SPEED);
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
    double radiusFeet = SmartDashboard.getNumber("CircleRadiusFeet", DriveConstants.DEFAULT_CIRCLE_RADIUS_FEET);
    double trackFeet = SmartDashboard.getNumber("TrackWidthFeet", DriveConstants.TRACK_WIDTH_FEET);
    double base = SmartDashboard.getNumber("BaseSpeed", DriveConstants.BASE_SPEED);

    if (autoController == null) {
      autoController = new CircleAutoController(radiusFeet, trackFeet, base);
    }
    double[] sideOutputs = autoController.computeSideOutputs();
    SmartDashboard.putNumber("LeftOutput", sideOutputs[0]);
    SmartDashboard.putNumber("RightOutput", sideOutputs[1]);
    drivetrain.tank(sideOutputs[0], sideOutputs[1]);
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Leave teleop empty (no driver inputs specified)
  }

  @Override
  public void disabledInit() {
    if (drivetrain != null) {
      drivetrain.stop();
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
  // CircleTankKinematics now encapsulates the circle math.
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.constants.DriveConstants;
import frc.robot.hardware.DualMotor;

/**
 * Encapsulates the 4-motor differential drive hardware and basic control API.
 */
public class Drivetrain {
  private MotorController left;
  private MotorController right;
  private DifferentialDrive drive;

  public Drivetrain() {
    var lf = new PWMVictorSPX(DriveConstants.LEFT_FRONT_PWM);
    var lr = new PWMVictorSPX(DriveConstants.LEFT_REAR_PWM);
    var rf = new PWMVictorSPX(DriveConstants.RIGHT_FRONT_PWM);
    var rr = new PWMVictorSPX(DriveConstants.RIGHT_REAR_PWM);
    MotorController l = new DualMotor(lf, lr);
    MotorController r = new DualMotor(rf, rr);
    init(l, r, true);
  }

  public Drivetrain(MotorController left, MotorController right) {
    init(left, right, false);
  }

  public Drivetrain(MotorController left, MotorController right, boolean invertRight) {
    init(left, right, invertRight);
  }

  private void init(MotorController l, MotorController r, boolean invertRight) {
    this.left = l;
    this.right = r;
    this.right.setInverted(invertRight);
    this.drive = new DifferentialDrive(this.left, this.right);
    this.drive.setSafetyEnabled(false);
  }

  public void tank(double leftOut, double rightOut) {
    drive.tankDrive(leftOut, rightOut, false);
  }

  public void stop() {
    drive.tankDrive(0.0, 0.0);
  }
}

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
  private final MotorController left;
  private final MotorController right;
  private final DifferentialDrive drive;

  public Drivetrain() {
    var lf = new PWMVictorSPX(DriveConstants.LEFT_FRONT_PWM);
    var lr = new PWMVictorSPX(DriveConstants.LEFT_REAR_PWM);
    var rf = new PWMVictorSPX(DriveConstants.RIGHT_FRONT_PWM);
    var rr = new PWMVictorSPX(DriveConstants.RIGHT_REAR_PWM);
    left = new DualMotor(lf, lr);
    right = new DualMotor(rf, rr);
    // Right side invert
    right.setInverted(true);

    drive = new DifferentialDrive(left, right);
    drive.setSafetyEnabled(false);
  }

  public void tank(double leftOut, double rightOut) {
    drive.tankDrive(leftOut, rightOut, false);
  }

  public void stop() {
    drive.tankDrive(0.0, 0.0);
  }
}

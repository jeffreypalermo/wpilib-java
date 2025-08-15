package frc.robot.hardware;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

/** Simple two-motor group implementing MotorController to avoid deprecated groups. */
public class DualMotor implements MotorController {
  private final MotorController a;
  private final MotorController b;
  private boolean inverted;

  public DualMotor(MotorController a, MotorController b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public void set(double speed) {
    double out = inverted ? -speed : speed;
    a.set(out);
    b.set(out);
  }

  @Override
  public double get() {
    return a.get();
  }

  @Override
  public void setInverted(boolean isInverted) {
    this.inverted = isInverted;
  }

  @Override
  public boolean getInverted() {
    return inverted;
  }

  @Override
  public void disable() {
    a.disable();
    b.disable();
  }

  @Override
  public void stopMotor() {
    a.stopMotor();
    b.stopMotor();
  }
}

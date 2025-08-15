package frc.robot.hardware;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

class DualMotorTest {
  static class FakeMotor implements MotorController {
    double last;
    boolean inverted;
    boolean disabled;
    @Override public void set(double speed) { last = inverted ? -speed : speed; }
    @Override public double get() { return last; }
    @Override public void setInverted(boolean isInverted) { inverted = isInverted; }
    @Override public boolean getInverted() { return inverted; }
    @Override public void disable() { disabled = true; }
    @Override public void stopMotor() { last = 0; }
  }

  @Test
  void forwardsMirrorsToBoth() {
    FakeMotor a = new FakeMotor();
    FakeMotor b = new FakeMotor();
    DualMotor dm = new DualMotor(a, b);
    dm.set(0.3);
    assertEquals(0.3, a.get(), 1e-9);
    assertEquals(0.3, b.get(), 1e-9);
  }

  @Test
  void inversionApplied() {
    FakeMotor a = new FakeMotor();
    FakeMotor b = new FakeMotor();
    DualMotor dm = new DualMotor(a, b);
    dm.setInverted(true);
    dm.set(0.25);
    assertEquals(-0.25, a.get(), 1e-9);
    assertEquals(-0.25, b.get(), 1e-9);
  }
}

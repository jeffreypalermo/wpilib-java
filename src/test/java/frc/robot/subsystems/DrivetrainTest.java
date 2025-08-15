package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

// Light smoke test: construct and stop without runtime errors using fakes (no HAL).
class DrivetrainTest {
  static class FakeMotor implements MotorController {
    double last; boolean inv; @Override public void set(double speed){ last = inv?-speed:speed; }
    @Override public double get(){ return last; } @Override public void setInverted(boolean i){ inv=i; }
    @Override public boolean getInverted(){ return inv; } @Override public void disable(){}
    @Override public void stopMotor(){ last = 0; }
  }

  @Test
  void constructAndStop() {
    var l = new FakeMotor();
    var r = new FakeMotor();
    Drivetrain dt = new Drivetrain(l, r, true);
    assertDoesNotThrow(dt::stop);
  }
}

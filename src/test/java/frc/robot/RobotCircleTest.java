package frc.robot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import frc.robot.math.CircleTankKinematics;

class RobotCircleTest {
  @Test
  @DisplayName("Inner wheel slower than outer for circle")
  void innerSlowerThanOuter() {
  double[] lr = CircleTankKinematics.tankOutputs(10.0, 2.0, 0.5);
    assertEquals(0.5, Math.max(lr[0], lr[1]), 1e-9, "One side should hit base speed");
    assertTrue(lr[0] < lr[1], "Left should be inner and slower when turning left by convention");
    assertTrue(lr[0] >= 0 && lr[1] >= 0, "Both outputs should be forward for positive radius");
  }

  @Test
  @DisplayName("Clamped outputs within [-1,1]")
  void outputsClamped() {
  double[] lr = CircleTankKinematics.tankOutputs(10.0, 100.0, 1.0);
    assertTrue(lr[0] >= -1 && lr[0] <= 1);
    assertTrue(lr[1] >= -1 && lr[1] <= 1);
  }

  @Test
  @DisplayName("Tiny radius still finite outputs")
  void tinyRadiusFinite() {
  double[] lr = CircleTankKinematics.tankOutputs(0.0001, 2.0, 0.4);
    assertTrue(Double.isFinite(lr[0]) && Double.isFinite(lr[1]));
    assertEquals(0.4, Math.max(lr[0], lr[1]), 1e-9);
  }

  @Test
  @DisplayName("Zero/negative inputs handled")
  void guardRails() {
  double[] lr = CircleTankKinematics.tankOutputs(-5.0, -1.0, -0.2);
    assertTrue(lr[0] >= -1 && lr[0] <= 1);
    assertTrue(lr[1] >= -1 && lr[1] <= 1);
  }
}

package frc.robot.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleDifferentialKinematicsTest {
  @Test
  void outerFasterThanInner() {
    double[] lr = CircleDifferentialKinematics.computeSideOutputs(10.0, 2.0, 0.5);
    assertEquals(0.5, Math.max(lr[0], lr[1]), 1e-9);
    assertTrue(lr[1] > lr[0]);
  }

  @Test
  void clampsOutputs() {
    double[] lr = CircleDifferentialKinematics.computeSideOutputs(10.0, 100.0, 1.0);
    assertTrue(lr[0] >= -1 && lr[0] <= 1);
    assertTrue(lr[1] >= -1 && lr[1] <= 1);
  }
}

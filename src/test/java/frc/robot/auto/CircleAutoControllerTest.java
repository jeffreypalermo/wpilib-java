package frc.robot.auto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircleAutoControllerTest {
  @Test
  void producesConsistentSideOutputs() {
    CircleAutoController c = new CircleAutoController(10.0, 2.0, 0.4);
    double[] a = c.computeSideOutputs();
    double[] b = c.computeSideOutputs();
    assertArrayEquals(a, b, 1e-12);
  }
}

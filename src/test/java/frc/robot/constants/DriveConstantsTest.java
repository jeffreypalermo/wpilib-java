package frc.robot.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DriveConstantsTest {
  @Test
  void valuesAreSane() {
    assertTrue(DriveConstants.TRACK_WIDTH_FEET > 0.0);
    assertTrue(DriveConstants.DEFAULT_CIRCLE_RADIUS_FEET > 0.0);
    assertTrue(DriveConstants.BASE_SPEED >= 0.0 && DriveConstants.BASE_SPEED <= 1.0);
  }
}

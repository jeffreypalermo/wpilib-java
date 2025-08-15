package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Light smoke test: construct and stop without runtime errors.
class DrivetrainTest {
  @Test
  void constructAndStop() {
    Drivetrain dt = new Drivetrain();
    assertDoesNotThrow(() -> dt.stop());
  }
}

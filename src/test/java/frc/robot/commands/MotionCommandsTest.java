package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

class MotionCommandsTest {
  static class FakeMotor implements MotorController {
    double last; boolean inv;
    @Override public void set(double speed){ last = inv? -speed : speed; }
    @Override public double get(){ return last; }
    @Override public void setInverted(boolean isInverted){ inv = isInverted; }
    @Override public boolean getInverted(){ return inv; }
    @Override public void disable(){}
    @Override public void stopMotor(){ last = 0; }
  }

  static class FakeDrivetrain extends Drivetrain {
    double lastL, lastR; int stops;
    FakeDrivetrain() { super(new FakeMotor(), new FakeMotor(), true); }
    @Override public void tank(double l, double r) { lastL = l; lastR = r; }
    @Override public void stop() { stops++; }
  }

  @Test
  void driveForwardDistanceCompletes() {
    FakeDrivetrain dt = new FakeDrivetrain();
    DriveForwardDistance cmd = new DriveForwardDistance(dt, 1.0, 1.0);
    cmd.initialize();
    for (int i = 0; i < 2; i++) cmd.execute(0.6); // 1.2ft traveled
    assertTrue(cmd.isFinished());
    cmd.end();
    assertTrue(dt.stops > 0);
  }

  @Test
  void turnInPlaceSigns() {
    FakeDrivetrain dt = new FakeDrivetrain();
    TurnInPlaceDegrees cmd = new TurnInPlaceDegrees(dt, -90.0, 180.0); // clockwise
    cmd.initialize();
    cmd.execute(0.1);
    assertTrue(dt.lastL > 0 && dt.lastR < 0);
  assertFalse(cmd.isFinished());
  cmd.end();
  assertTrue(dt.stops > 0);
  }

  @Test
  void circleProducesDifferentialOutputs() {
    FakeDrivetrain dt = new FakeDrivetrain();
    TurnCircleRadius cmd = new TurnCircleRadius(dt, 10.0, 0.4);
    cmd.initialize();
    cmd.execute(0.02);
    assertNotEquals(dt.lastL, dt.lastR);
    assertFalse(cmd.isFinished());
    cmd.end();
    assertTrue(dt.stops > 0);
  }

  @Test
  void driveForwardAtSpeedProducesEqualOutputsAndStopsOnEnd() {
    FakeDrivetrain dt = new FakeDrivetrain();
    DriveForwardAtSpeed cmd = new DriveForwardAtSpeed(dt, 1.0);
    cmd.initialize();
    cmd.execute(0.02);
    assertEquals(dt.lastL, dt.lastR, 1e-9);
    assertFalse(cmd.isFinished());
    cmd.end();
    assertTrue(dt.stops > 0);
  }
}

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.commands.*;
import frc.robot.constants.DriveConstants;

public final class Main {
  private Main() {}

  public static void main(String... args) {
    RobotBase.startRobot(() -> {
      Robot robot = new Robot();
      // Example plan: can be replaced by parsing args or external config
      // Commands: circle radius 10, drive forward 2 ft, turn right 90 deg, drive forward 0.10 ft/s, stop, spin right in place 720 deg
      Command circle = new TurnCircleRadius(robotDrivetrain(robot), 10.0, DriveConstants.BASE_SPEED);
      Command drive2ft = new DriveForwardDistance(robotDrivetrain(robot), 2.0, DriveConstants.DEFAULT_LINEAR_SPEED_FTPS);
      Command turnRight90 = new TurnInPlaceDegrees(robotDrivetrain(robot), -90.0, DriveConstants.DEFAULT_ANGULAR_SPEED_DPS);
      Command driveAtPoint1 = new DriveForwardAtSpeed(robotDrivetrain(robot), 0.10);
      Command stop = new Command() { public void initialize() { robotDrivetrain(robot).stop(); } public void execute(double dt) {} public boolean isFinished() { return true; } public void end() {} };
      Command spinRight720 = new TurnInPlaceDegrees(robotDrivetrain(robot), -720.0, 180.0);

      Command plan = new SequentialCommandGroup(
        // Run circle for a short time in parallel with a timed stopper
        new ParallelCommandGroup(circle /* would run until canceled */),
        drive2ft,
        turnRight90,
        driveAtPoint1,
        stop,
        spinRight720
      );
      robot.setAutonomousPlan(plan);
      return robot;
    });
  }

  private static frc.robot.subsystems.Drivetrain robotDrivetrain(Robot robot) {
    // Accessor so Main can pass the same drivetrain into commands
    // In a larger app, Robot would expose subsystems more formally.
    try {
      var field = Robot.class.getDeclaredField("drivetrain");
      field.setAccessible(true);
      return (frc.robot.subsystems.Drivetrain) field.get(robot);
    } catch (ReflectiveOperationException e) {
      throw new IllegalStateException("Unable to access drivetrain", e);
    }
  }
}

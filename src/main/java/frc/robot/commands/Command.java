package frc.robot.commands;

public interface Command {
  void initialize();
  void execute(double dtSeconds);
  boolean isFinished();
  void end();
}

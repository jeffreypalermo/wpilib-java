package frc.robot.commands;

public class SequentialCommandGroup implements Command {
  private final CommandScheduler scheduler = new CommandScheduler();
  private final Command[] commands;
  private boolean started;

  public SequentialCommandGroup(Command... commands) {
    this.commands = commands;
  }

  @Override
  public void initialize() {
    scheduler.scheduleSequential(commands);
    started = true;
  }

  @Override
  public void execute(double dtSeconds) {
    scheduler.run();
  }

  @Override
  public boolean isFinished() {
    return started && scheduler.isIdle();
  }

  @Override
  public void end() {
    scheduler.cancelAll();
  }
}

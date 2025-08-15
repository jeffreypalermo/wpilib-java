package frc.robot.commands;

public class ParallelCommandGroup implements Command {
  private final CommandScheduler scheduler = new CommandScheduler();
  private final Command[] commands;
  private boolean started;

  public ParallelCommandGroup(Command... commands) {
    this.commands = commands;
  }

  @Override
  public void initialize() {
    scheduler.scheduleParallel(commands);
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

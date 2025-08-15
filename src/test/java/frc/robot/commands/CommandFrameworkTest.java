package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommandFrameworkTest {
  static class ProbeCommand implements Command {
    int init, exec, end;
    int stepsToFinish;
    ProbeCommand(int steps) { this.stepsToFinish = steps; }
    @Override public void initialize() { init++; }
    @Override public void execute(double dt) { exec++; stepsToFinish--; }
    @Override public boolean isFinished() { return stepsToFinish <= 0; }
    @Override public void end() { end++; }
  }

  @Test
  void sequentialRunsInOrder() {
    CommandScheduler s = new CommandScheduler();
    ProbeCommand a = new ProbeCommand(2);
    ProbeCommand b = new ProbeCommand(1);
    s.scheduleSequential(a, b);
    for (int i = 0; i < 5; i++) s.run();
    assertTrue(s.isIdle());
    assertTrue(a.init > 0 && a.exec > 0 && a.end > 0);
    assertTrue(b.init > 0 && b.exec > 0 && b.end > 0);
  }

  @Test
  void parallelRunsTogether() {
    CommandScheduler s = new CommandScheduler();
    ProbeCommand a = new ProbeCommand(2);
    ProbeCommand b = new ProbeCommand(3);
    s.scheduleParallel(a, b);
    for (int i = 0; i < 5; i++) s.run();
    assertTrue(s.isIdle());
    assertTrue(a.exec >= 2 && b.exec >= 3);
  }

  @Test
  void groupsWork() {
    ProbeCommand a = new ProbeCommand(1);
    ProbeCommand b = new ProbeCommand(1);
    SequentialCommandGroup seq = new SequentialCommandGroup(a, b);
    seq.initialize();
    for (int i = 0; i < 5; i++) seq.execute(0.02);
    assertTrue(seq.isFinished());
    seq.end();

    ProbeCommand c = new ProbeCommand(1);
    ProbeCommand d = new ProbeCommand(1);
    ParallelCommandGroup par = new ParallelCommandGroup(c, d);
    par.initialize();
    for (int i = 0; i < 5; i++) par.execute(0.02);
    assertTrue(par.isFinished());
    par.end();
  }
}

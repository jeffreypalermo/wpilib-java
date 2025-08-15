package frc.robot.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Very small command scheduler that supports sequential and parallel groups. */
public class CommandScheduler {
  private final LinkedList<Command> queue = new LinkedList<>();
  private final List<Command> running = new ArrayList<>();
  private long lastNanos = System.nanoTime();

  public void scheduleSequential(Command... commands) {
    for (Command c : commands) queue.add(c);
  }

  public void scheduleParallel(Command... commands) {
    for (Command c : commands) running.add(c);
  }

  /** Call periodically; runs current sequential and all parallel commands. */
  public void run() {
    long now = System.nanoTime();
    double dt = (now - lastNanos) / 1_000_000_000.0;
    lastNanos = now;

    // Start next sequential if needed
    if (!queue.isEmpty() && running.stream().noneMatch(c -> c == queue.peek())) {
      Command head = queue.peek();
      if (!running.contains(head)) {
        head.initialize();
        running.add(head);
      }
    }

    // Execute
    List<Command> toRemove = new ArrayList<>();
    for (Command c : running) {
      c.execute(dt);
      if (c.isFinished()) {
        c.end();
        toRemove.add(c);
        if (!queue.isEmpty() && c == queue.peek()) queue.poll();
      }
    }
    running.removeAll(toRemove);
  }

  public boolean isIdle() {
    return queue.isEmpty() && running.isEmpty();
  }

  public void cancelAll() {
    for (Command c : running) {
      c.end();
    }
    running.clear();
    queue.clear();
  }
}

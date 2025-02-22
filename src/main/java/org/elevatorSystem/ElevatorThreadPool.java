package org.elevatorSystem;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ElevatorThreadPool {
    private final ExecutorService executor;
    private final ConcurrentHashMap<Integer, Future<?>> runningElevators = new ConcurrentHashMap<>();

    public ElevatorThreadPool(int numElevators) {
        this.executor = Executors.newFixedThreadPool(numElevators);
    }

    public void submitTask(int elevatorId, Runnable task) {
        runningElevators.compute(elevatorId, (id, future) -> {
            if (future == null || future.isDone()) {
                return executor.submit(task);
            }
            return future; // Task already running, don't submit again
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}

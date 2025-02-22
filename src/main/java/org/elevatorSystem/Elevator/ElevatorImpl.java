package org.elevatorSystem.Elevator;

import org.elevatorSystem.Button.Button;
import org.elevatorSystem.Button.LiftButton;
import org.elevatorSystem.ElevatorThreadPool;
import org.elevatorSystem.Enums.Direction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorImpl implements Elevator, Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    ElevatorThreadPool elevatorThreadPool;
    private final Integer elevatorNo;
    Direction direction;
    Integer currentFloor;
    List<Button> buttonList = new ArrayList<>();
    PriorityBlockingQueue<Integer> upQ = new PriorityBlockingQueue<>();
    PriorityBlockingQueue<Integer> downQ = new PriorityBlockingQueue<>(100, Comparator.reverseOrder());

    public ElevatorImpl(int n, int elevatorNo, ElevatorThreadPool elevatorThreadPool) {
        currentFloor = 0;
        this.elevatorNo = elevatorNo;
        this.elevatorThreadPool = elevatorThreadPool;
        direction = Direction.IDLE;
        for (int i = 1; i <= n; i++) {
            buttonList.add(new LiftButton(i, this));
        }
    }

    @Override
    public void addFloor(Integer floorNo) {
        lock.lock();
        boolean goingUp = false;
        try {
            if (currentFloor <= floorNo) {
                upQ.add(floorNo);
                goingUp = true;
            } else {
                downQ.add(floorNo);
            }
            if (direction == Direction.IDLE) {
                setDirection(goingUp ? Direction.UP : Direction.DOWN);
                elevatorThreadPool.submitTask(elevatorNo, this);
            }
        } finally {
            lock.unlock();
        }
    }

    private void open() {
        System.out.printf(String.format("opening doors for lift %d%n", elevatorNo));
        try {
            lock.lock();
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }

    }
    private synchronized void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    private void close() {
        System.out.printf(String.format("closing doors for lift %d%n", elevatorNo));
    }

    private void handlePress(Integer floorNo) {
        addFloor(floorNo);
    }

    @Override
    public void run() {
        while (true) {
            Integer nextFloor = null;

            lock.lock();
            try {
                if (upQ.isEmpty() && downQ.isEmpty()) {
                    direction = Direction.IDLE;
                    break;
                }

                if (direction == Direction.UP && !upQ.isEmpty()) {
                    nextFloor = upQ.poll();
                    if (nextFloor < currentFloor) {
                        downQ.add(nextFloor);
                        continue;
                    }
                } else if (direction == Direction.DOWN && !downQ.isEmpty()) {
                    nextFloor = downQ.poll();
                    if (nextFloor > currentFloor) {
                        upQ.add(nextFloor);
                        continue;
                    }
                }
            } finally {
                lock.unlock();
            }

            // Move to next floor (OUTSIDE the lock)
            if (nextFloor != null) {
                currentFloor = nextFloor;
                open();
                close();
            }

            // Adjust direction after processing all requests
            lock.lock();
            try {
                if (direction == Direction.UP && upQ.isEmpty()) {
                    direction = downQ.isEmpty() ? Direction.IDLE : Direction.DOWN;
                } else if (direction == Direction.DOWN && downQ.isEmpty()) {
                    direction = upQ.isEmpty() ? Direction.IDLE : Direction.UP;
                }
            } finally {
                lock.unlock();
            }
        }
    }

}

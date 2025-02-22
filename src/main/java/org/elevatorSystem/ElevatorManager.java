package org.elevatorSystem;

import lombok.Data;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.Button.FloorButton;
import org.elevatorSystem.Elevator.Elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class ElevatorManager {
    private ElevatorManager(){};
    public static class Holder{
        public static final ElevatorManager INSTANCE = new ElevatorManager();
    }

    public static ElevatorManager getInstance(){
        return Holder.INSTANCE;
    }
    List<Elevator> elevatorList= new ArrayList<>();

    public synchronized void addRequest(Button button) {
        //TODO: Assign best lift
        int n = elevatorList.size();
        Random random = new Random();
        int randomIndex = random.nextInt(n);
        elevatorList.get(randomIndex).addFloor(((FloorButton)button).getFloorNo());
    }
}

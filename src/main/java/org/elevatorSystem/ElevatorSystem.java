package org.elevatorSystem;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.Button.FloorButton;
import org.elevatorSystem.Elevator.Elevator;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElevatorSystem {
    private ElevatorSystem(){

    };
    public static ElevatorSystem elevatorSystem = null;

    public static ElevatorSystem getelevatorSystem(){
        synchronized (ElevatorSystem.class){
            if(elevatorSystem != null){
                return elevatorSystem;
            }
            synchronized (ElevatorSystem.class){
                elevatorSystem = new ElevatorSystem();
                return elevatorSystem;
            }
        }
    }
    List<Elevator> elevatorList= new ArrayList<>();

    public synchronized void addRequest(Button button) {
        //TODO: Find best elevator
        elevatorList.getFirst().addFloor(((FloorButton)button).getFloorNo());
    }
}

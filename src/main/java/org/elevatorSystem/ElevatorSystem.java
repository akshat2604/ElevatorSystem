package org.elevatorSystem;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.Button.FloorButton;
import org.elevatorSystem.Elevator.Elevator;

import java.util.List;

@NoArgsConstructor
@Data
public class ElevatorSystem {
    List<Elevator> elevatorList;

    public synchronized void addRequest(Button button) {
        //TODO: Find best elevator
        elevatorList.getFirst().addFloor(((FloorButton)button).getFloorNo());
    }
}

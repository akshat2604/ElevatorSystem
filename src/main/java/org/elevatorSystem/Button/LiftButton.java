package org.elevatorSystem.Button;

import lombok.AllArgsConstructor;
import org.elevatorSystem.Elevator.Elevator;

@AllArgsConstructor
public class LiftButton implements Button{
    Integer floorNo;
    Elevator elevator;

    @Override
    public void press() {
        elevator.addFloor(floorNo);
    }
}

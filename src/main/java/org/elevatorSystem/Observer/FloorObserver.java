package org.elevatorSystem.Observer;

import lombok.AllArgsConstructor;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.ElevatorSystem;

@AllArgsConstructor
public class FloorObserver implements Observer {
    ElevatorSystem elevatorSystem;

    @Override
    public void addRequest(Button button) {
        elevatorSystem.addRequest(button);
    }
}

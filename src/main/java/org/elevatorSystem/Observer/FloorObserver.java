package org.elevatorSystem.Observer;

import lombok.AllArgsConstructor;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.ElevatorManager;

@AllArgsConstructor
public class FloorObserver implements Observer {
    ElevatorManager elevatorManager;

    @Override
    public void addRequest(Button button) {
        elevatorManager.addRequest(button);
    }
}

package org.elevatorSystem;

import org.elevatorSystem.Elevator.ElevatorImpl;
import org.elevatorSystem.Observer.FloorObserver;
import org.elevatorSystem.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
        Observer observer = new FloorObserver(elevatorSystem);
        int n = 5;
        List<FloorTerminal> floorTerminalList = new ArrayList<>();
        for(int i=0;i<n;i++){
            floorTerminalList.add(new FloorTerminal(i+1, observer));
        }
        ElevatorThreadPool elevatorThreadPool = new ElevatorThreadPool(2);
        elevatorSystem.getElevatorList().add(new ElevatorImpl(n,1, elevatorThreadPool));
        elevatorSystem.getElevatorList().add(new ElevatorImpl(n, 2, elevatorThreadPool));

        floorTerminalList.get(4).getButtonList().get(0).press();//up
        floorTerminalList.get(2).getButtonList().get(1).press();//down

    }
}
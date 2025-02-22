package org.elevatorSystem;

import lombok.Data;
import org.elevatorSystem.Button.Button;
import org.elevatorSystem.Button.FloorButton;
import org.elevatorSystem.Enums.Direction;
import org.elevatorSystem.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

@Data
public class FloorTerminal {
    Integer floorNo;
    List<Button> buttonList = new ArrayList<>();

    public FloorTerminal(int floorNo, Observer observer){
        this.floorNo = floorNo;
        Button button1 = new FloorButton(Direction.UP, floorNo, observer);
        Button button2 = new FloorButton(Direction.DOWN, floorNo, observer);
        this.buttonList.add(button1);
        this.buttonList.add(button2);
    }

    public FloorTerminal(int floorNo, Direction uniDirection,  Observer observer){
        this.floorNo = floorNo;
        Button button = new FloorButton(uniDirection, floorNo, observer);
        this.buttonList.add(button);
    }

    void pressButton(){

    }
}

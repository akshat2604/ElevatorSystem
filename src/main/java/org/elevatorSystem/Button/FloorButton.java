package org.elevatorSystem.Button;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.elevatorSystem.Enums.Direction;
import org.elevatorSystem.Observer.Observer;
@Data
@AllArgsConstructor
public class FloorButton implements Button {
    Direction direction;
    Integer floorNo;
    Observer observer;

    @Override
    public void press(){
        observer.addRequest(this);
    }
}

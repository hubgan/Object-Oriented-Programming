package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public MoveDirection[] parse(String[] args) {
        List<MoveDirection> list = new LinkedList<>();

        for (String arg: args) {
            switch (arg) {
                case "f", "forward" -> list.add(MoveDirection.FORWARD);
                case "b", "backward" -> list.add(MoveDirection.BACKWARD);
                case "r", "right" -> list.add(MoveDirection.RIGHT);
                case "l", "left" -> list.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }

        return list.toArray(new MoveDirection[0]);
    }
}

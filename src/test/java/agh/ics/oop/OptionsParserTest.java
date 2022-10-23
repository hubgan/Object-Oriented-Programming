package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void testParse() {
        OptionsParser parser = new OptionsParser();

        String[] firstInput = new String[]{"f", "f", "b", "l", "r", "backward", "right", "left", "forward", "b"};
        MoveDirection[] firstOutput = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        MoveDirection[] parsedFirstInput = parser.parse(firstInput);

        for (int i = 0; i < 10; i++) {
            assertEquals(firstOutput[i], parsedFirstInput[i]);
        }

        String[] secondInput = new String[]{"b", "FORWARD", "first", "l", "lazy", "f", "F", "forward", "r", "R", "l", "b",
                "bad", "r", "random", "r", "R", "LEFT", "f", "RIGHT", "f", "BACKWARD", "b"};
        MoveDirection[] secondOutput = new MoveDirection[]{MoveDirection.BACKWARD, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD};
        MoveDirection[] parsedSecondInput = parser.parse(secondInput);

        for (int i = 0; i < 12; i++) {
            assertEquals(secondOutput[i], parsedSecondInput[i]);
        }
    }
}

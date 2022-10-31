package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {
    @Test
    void testRun() {
        OptionsParser parser = new OptionsParser();
        String[] firstInput = new String[]{"r", "l", "f", "r", "f", "f", "f", "f", "r", "f", "r", "f", "f", "f",
                "r", "f", "f", "f"};
        MoveDirection[] parsedFirstInput = parser.parse(firstInput);

        Vector2d[] firstTestPositions = new Vector2d[]{
                new Vector2d(4, 4),
                new Vector2d(2, 2),
                new Vector2d(0, 0)
        };

        Vector2d[] firstPositions = new Vector2d[]{new Vector2d(4, 0),
                new Vector2d(1, 4), new Vector2d(0, 1)};

        IWorldMap firstMap = new RectangularMap(5, 5);
        IEngine firstEngine = new SimulationEngine(parsedFirstInput, firstMap, firstTestPositions);
        firstEngine.run();

        for (int i = 0; i < 3; i++) {
            Object animal = firstMap.objectAt(firstPositions[i]);
            if (animal instanceof Animal that) {
                assertEquals(firstPositions[i], that.getPosition());
            }
            else {
                assertNull(animal);
            }
        }

        String[] secondInput = new String[]{"r", "f", "r", "f", "f", "f", "f", "f", "f", "r", "f", "f", "r", "f",
                "f", "f"};
        MoveDirection[] parsedSecondInput = parser.parse(secondInput);

        Vector2d[] secondTestPositions = new Vector2d[]{
                new Vector2d(4, 4),
                new Vector2d(0, 0),
        };

        Vector2d[] secondPositions = new Vector2d[]{new Vector2d(3, 0),
                new Vector2d(3, 4)};

        IWorldMap secondMap = new RectangularMap(5, 5);
        IEngine secondEngine = new SimulationEngine(parsedSecondInput, secondMap, secondTestPositions);
        secondEngine.run();

        for (int i = 0; i < 2; i++) {
            Object animal = secondMap.objectAt(secondPositions[i]);
            if (animal instanceof Animal that) {
                assertEquals(secondPositions[i], that.getPosition());
            }
            else {
                assertNull(animal);
            }
        }
    }
}

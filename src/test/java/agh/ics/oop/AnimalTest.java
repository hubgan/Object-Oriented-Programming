package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    public void testOrientation() {
        OptionsParser parser = new OptionsParser();

        Animal firstTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] firstInput = new String[]{"forward", "forward", "right", "forward", "f", "f", "left", "l", "f", "r", "b"};
        MoveDirection[] parsedFirstInput = parser.parse(firstInput);
        MapDirection[] firstOutput = new MapDirection[]{MapDirection.NORTH, MapDirection.NORTH, MapDirection.EAST,
                MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.NORTH, MapDirection.WEST,
                MapDirection.WEST, MapDirection.NORTH, MapDirection.NORTH};

        for (int i = 0; i < 11; i++) {
            firstTestAnimal.move(parsedFirstInput[i]);
            assertEquals(firstOutput[i], firstTestAnimal.getOrientation());
        }

        Animal secondTestAnimal = new Animal(new RectangularMap(5, 5), new Vector2d(2, 2));
        String[] secondInput = new String[]{"r", "FORWARD", "f", "f", "first", "f", "second", "l", "RIGHT", "f", "f",
                "right", "f"};
        MoveDirection[] parsedSecondInput = parser.parse(secondInput);
        MapDirection[] secondOutput = new MapDirection[]{MapDirection.EAST, MapDirection.EAST, MapDirection.EAST,
                MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.EAST,
                MapDirection.EAST};

        for (int i = 0; i < 9; i++) {
            secondTestAnimal.move(parsedSecondInput[i]);
            assertEquals(secondOutput[i], secondTestAnimal.getOrientation());
        }

        String[] thirdInput = new String[]{"r", "l", "f", "r", "f", "f", "f", "f", "r", "f", "r", "f", "f", "f",
                "r", "f", "f", "f"};
        MoveDirection[] parsedThirdInput = parser.parse(thirdInput);

        Vector2d[] testPositions = new Vector2d[]{
                new Vector2d(4, 4),
                new Vector2d(2, 2),
                new Vector2d(0, 0)
        };

        MapDirection[] thirdOutput = new MapDirection[]{MapDirection.SOUTH, MapDirection.NORTH, MapDirection.SOUTH};

        IWorldMap map = new RectangularMap(5, 5);
        IEngine engine = new SimulationEngine(parsedThirdInput, map, testPositions);
        engine.run();

        Vector2d[] positions = new Vector2d[]{new Vector2d(4, 0),
                new Vector2d(1, 4), new Vector2d(0, 1)};

        for (int i = 0; i < 3; i++) {
            Object animal = map.objectAt(positions[i]);
            if (animal instanceof Animal) {
                Animal that = (Animal) animal;
                assertEquals(thirdOutput[i], that.getOrientation());
            }
        }
    }

    @Test
    public void testPosition() {
        OptionsParser parser = new OptionsParser();

        Animal firstTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] firstInput = new String[]{"forward", "forward", "right", "forward", "f", "f", "left", "l", "f", "r", "b"};
        MoveDirection[] parsedFirstInput = parser.parse(firstInput);
        Vector2d[] firstOutput = new Vector2d[]{new Vector2d(2, 3), new Vector2d(2, 4), new Vector2d(2, 4),
        new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(4, 4), new Vector2d(4, 4),
        new Vector2d(4, 4), new Vector2d(3, 4), new Vector2d(3, 4), new Vector2d(3, 3)};

        for (int i = 0; i < 11; i++) {
            firstTestAnimal.move(parsedFirstInput[i]);
            assertEquals(firstOutput[i], firstTestAnimal.getPosition());
        }

        Animal secondTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] secondInput = new String[]{"f", "first", "l", "f", "left", "BACKWARD", "b", "bad", "b", "r", "RIGHT",
                "b", "b", "b", "b"};
        MoveDirection[] parsedSecondInput = parser.parse((secondInput));
        Vector2d[] secondOutput = new Vector2d[]{new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(1, 3),
        new Vector2d(1, 3), new Vector2d(1, 4), new Vector2d(1, 4), new Vector2d(1, 4),
        new Vector2d(2, 4), new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(4, 4)};

        for (int i = 0; i < 11; i++) {
            secondTestAnimal.move(parsedSecondInput[i]);
            assertEquals(secondOutput[i], secondTestAnimal.getPosition());
        }

        String[] thirdInput = new String[]{"r", "l", "f", "r", "f", "f", "f", "f", "r", "f", "r", "f", "f", "f",
                "r", "f", "f", "f"};
        MoveDirection[] parsedThirdInput = parser.parse(thirdInput);

        Vector2d[] testPositions = new Vector2d[]{
                new Vector2d(4, 4),
                new Vector2d(2, 2),
                new Vector2d(0, 0)
        };

        Vector2d[] thirdOutput = new Vector2d[]{new Vector2d(4, 0),
                new Vector2d(1, 4), new Vector2d(0, 1)};

        IWorldMap map = new RectangularMap(5, 5);
        IEngine engine = new SimulationEngine(parsedThirdInput, map, testPositions);
        engine.run();

        for (int i = 0; i < 3; i++) {
            assertTrue(map.isOccupied(thirdOutput[i]));
        }
    }

    @Test
    public void testIsInRange() {
        OptionsParser parser = new OptionsParser();

        Animal firstTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] firstInput = new String[]{"f", "f", "f", "f", "f"};
        MoveDirection[] parsedFirstInput = parser.parse(firstInput);
        Vector2d[] firstOutput = new Vector2d[]{new Vector2d(2, 3), new Vector2d(2, 4),
                new Vector2d(2, 4), new Vector2d(2, 4), new Vector2d(2, 4)};

        for (int i = 0; i < 5; i++) {
            firstTestAnimal.move(parsedFirstInput[i]);
            assertEquals(firstOutput[i], firstTestAnimal.getPosition());
        }

        Animal secondTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] secondInput = new String[]{"r", "f", "f", "f", "f", "f"};
        MoveDirection[] parsedSecondInput = parser.parse(secondInput);
        Vector2d[] secondOutput = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 2),
                new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 2)};

        for (int i = 0; i < 6; i++) {
            secondTestAnimal.move(parsedSecondInput[i]);
            assertEquals(secondOutput[i], secondTestAnimal.getPosition());
        }

        Animal thirdTestAnimal = new Animal(new RectangularMap(5,5));
        String[] thirdInput = new String[]{"l", "f", "f", "f", "f", "f"};
        MoveDirection[] parsedThirdInput = parser.parse(thirdInput);
        Vector2d[] thirdOutput = new Vector2d[]{new Vector2d(2, 2), new Vector2d(1, 2),
                new Vector2d(0, 2), new Vector2d(0, 2), new Vector2d(0, 2), new Vector2d(0, 2)};

        for (int i = 0; i < 6; i++) {
            thirdTestAnimal.move(parsedThirdInput[i]);
            assertEquals(thirdOutput[i], thirdTestAnimal.getPosition());
        }

        Animal fourthTestAnimal = new Animal(new RectangularMap(5, 5));
        String[] fourthInput = new String[]{"l", "l", "f", "f", "f", "f"};
        MoveDirection[] parsedFourthInput = parser.parse(fourthInput);
        Vector2d[] fourthOutput = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2),
                new Vector2d(2, 1), new Vector2d(2, 0), new Vector2d(2, 0), new Vector2d(2, 0)};

        for (int i = 0; i < 6; i++) {
            fourthTestAnimal.move(parsedFourthInput[i]);
            assertEquals(fourthOutput[i], fourthTestAnimal.getPosition());
        }
    }
}
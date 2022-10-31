package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    void testCanMoveTo() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = new Vector2d[]{new Vector2d(0, 0), new Vector2d(0, 4), new Vector2d(4, 0),
                new Vector2d(4, 4), new Vector2d(-1, -1), new Vector2d(-5, 0), new Vector2d(5, 0),
                new Vector2d(5, 5)};
        Boolean[] results = new Boolean[]{true, true, true, true, false, false, false, false};

        for (int i = 0; i < 8; i++) {
            assertEquals(results[i], map.canMoveTo(positions[i]));
        }
    }

    @Test
    void testPlace() {
        IWorldMap map = new RectangularMap(5, 5);

        assertTrue(map.place(new Animal(map, new Vector2d(0, 0))));
        assertTrue(map.place(new Animal(map, new Vector2d(2, 2))));
        assertTrue(map.place(new Animal(map, new Vector2d(4, 4))));
        assertTrue(map.place(new Animal(map, new Vector2d(1, 3))));
        assertFalse(map.place(new Animal(map, new Vector2d(0, 0))));
        assertFalse(map.place(new Animal(map, new Vector2d(2, 2))));
        assertFalse(map.place(new Animal(map, new Vector2d(4, 4))));
        assertFalse(map.place(new Animal(map, new Vector2d(1, 3))));
    }

    @Test
    void testIsOccupied() {
        IWorldMap map = new RectangularMap(5, 5);

        map.place(new Animal(map, new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(4, 4)));

        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(4, 4)));
        assertFalse(map.isOccupied(new Vector2d(1, 3)));

        map.place(new Animal(map, new Vector2d(1, 3)));
        assertTrue(map.isOccupied(new Vector2d(1, 3)));
    }

    @Test
    void testObjectAt() {
        IWorldMap map = new RectangularMap(5, 5);

        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal firstAnimal = new Animal(map, new Vector2d(2, 2));
        map.place(firstAnimal);
        assertEquals(firstAnimal, map.objectAt(new Vector2d(2, 2)));

        assertNull(map.objectAt(new Vector2d(1, 3)));
        Animal secondAnimal = new Animal(map, new Vector2d(1, 3));
        map.place(secondAnimal);
        assertEquals(secondAnimal, map.objectAt(new Vector2d(1, 3)));
    }
}

package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void testCanMoveTo() {
        GrassField map = new GrassField(10);
        assertTrue(map.canMoveTo(new Vector2d(4, 2)));
        assertTrue(map.canMoveTo(new Vector2d(-10, 20)));
        map.place(new Animal(map, new Vector2d(4, 2)));
        assertFalse(map.canMoveTo(new Vector2d(4, 2)));
    }

    @Test
    void testPlace() {
        GrassField map = new GrassField(10);
        assertTrue(map.place(new Animal(map, new Vector2d(4, 4))));
        assertFalse(map.place(new Animal(map, new Vector2d(4, 4))));
        assertTrue(map.place(new Animal(map, new Vector2d(-20, 10))));
        assertFalse(map.place(new Animal(map, new Vector2d(-20, 10))));
        assertTrue(map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MIN_VALUE))));
    }

    @Test
    void testIsOccupied() {
        GrassField firstMap = new GrassField(10);
        firstMap.place(new Animal(firstMap, new Vector2d(4, 4)));
        assertTrue(firstMap.isOccupied(new Vector2d(4, 4)));

        GrassField secondMap = new GrassField(0);
        assertFalse(secondMap.isOccupied(new Vector2d(4, 4)));
        secondMap.place(new Animal(secondMap, new Vector2d(4, 4)));
        assertTrue(secondMap.isOccupied(new Vector2d(4, 4)));
    }

    @Test
    void testObjectAt() {
        GrassField firstMap = new GrassField(10);
        Animal firstAnimal = new Animal(firstMap, new Vector2d(4, 4));
        firstMap.place(firstAnimal);
        assertEquals(firstAnimal, firstMap.objectAt(new Vector2d(4, 4)));

        GrassField secondMap = new GrassField(0);
        Animal secondAnimal = new Animal(secondMap, new Vector2d(10, 20));
        secondMap.place(secondAnimal);
        assertNull(secondMap.objectAt(new Vector2d(4, 4)));
        assertEquals(secondAnimal, secondMap.objectAt(new Vector2d(10, 20)));
    }
}

package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        assertEquals(new Vector2d(1, 1), new Vector2d(1, 1));
        assertNotEquals(new Vector2d(1, 1), new Vector2d(0, 1));
    }

    @Test
    public void testToString() {
        assertEquals("(1,1)", new Vector2d(1, 1).toString());
        assertNotEquals("(2,2)", new Vector2d(1, 1).toString());
    }

    @Test
    public void testPrecedes() {
        assertTrue(new Vector2d(0, 0).precedes(new Vector2d(1, 1)));
        assertTrue(new Vector2d(1, 0).precedes(new Vector2d(1, 1)));
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(0, 0)));
        assertFalse(new Vector2d(1, 1).precedes(new Vector2d(0, 1)));
    }

    @Test
    public void testFollows() {
        assertFalse(new Vector2d(0, 0).follows(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 0).follows(new Vector2d(1, 1)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(1, 1)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(0, 0)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(0, 1)));
    }

    @Test
    public void testUpperRight() {
        assertEquals(new Vector2d(3, 4), new Vector2d(3, 1).upperRight(new Vector2d(1, 4)));
        assertNotEquals(new Vector2d(3, 4), new Vector2d(3, 4).upperRight(new Vector2d(3, 5)));
    }

    @Test
    public void testLowerLeft() {
        assertEquals(new Vector2d(1, 1), new Vector2d(3, 1).lowerLeft(new Vector2d(1, 4)));
        assertNotEquals(new Vector2d(3, 5), new Vector2d(3, 4).lowerLeft(new Vector2d(3, 5)));
    }

    @Test
    public void testAdd() {
        assertEquals(new Vector2d(3, 5), new Vector2d(0, 1).add(new Vector2d(3, 4)));
        assertNotEquals(new Vector2d(3, 5), new Vector2d(0, 1).add(new Vector2d(4, 3)));
    }

    @Test
    public void testSubtract() {
        assertEquals(new Vector2d(-3, -3), new Vector2d(0, 1).subtract(new Vector2d(3, 4)));
        assertNotEquals(new Vector2d(-3, -3), new Vector2d(0, 1).subtract(new Vector2d(4, 3)));
    }

    @Test
    public void testOpposite() {
        assertEquals(new Vector2d(-1, -1), new Vector2d(1, 1).opposite());
        assertEquals(new Vector2d(0, 0), new Vector2d(0, 0).opposite());
        assertNotEquals(new Vector2d(-1, -1), new Vector2d(1, -1).opposite());
    }
}

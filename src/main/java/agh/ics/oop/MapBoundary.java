package agh.ics.oop;

import java.util.TreeMap;
import java.util.Comparator;

public class MapBoundary implements IPositionChangeObserver {
    private final TreeMap<Vector2d, Integer> xCords = new TreeMap<>(Comparator.comparingInt(elem -> elem.x));
    private final TreeMap<Vector2d, Integer> yCords = new TreeMap<>(Comparator.comparingInt(elem -> elem.y));

    public void remove(Vector2d position) {
        xCords.remove(position);
        yCords.remove(position);
    }

    public void add(Vector2d position) {
        xCords.put(position, position.x);
        yCords.put(position, position.y);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        remove(oldPosition);
        add(newPosition);
    }

    public Vector2d[] getMapBorders() {
        Integer xLower = xCords.get(xCords.firstKey());
        Integer yLower = yCords.get(yCords.firstKey());
        Integer xUpper = xCords.get(xCords.lastKey());
        Integer yUpper = yCords.get(yCords.lastKey());

        return new Vector2d[]{new Vector2d(xLower, yLower), new Vector2d(xUpper, yUpper)};
    }
}

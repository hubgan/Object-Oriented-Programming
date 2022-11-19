package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final HashMap<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();
    protected Vector2d lower = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upper = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.put(animal.getPosition(), animal);
            mapBoundary.add(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + " is already taken");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.elements.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.elements.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement animal = this.elements.get(oldPosition);
        this.elements.remove(oldPosition);
        this.elements.put(newPosition, animal);
        this.mapBoundary.remove(oldPosition);
        this.mapBoundary.add(newPosition);
    }

    public String toString() {
        Vector2d[] borders = mapBoundary.getMapBorders();
        this.lower = this.lower.lowerLeft(borders[0]);
        this.upper = this.upper.upperRight(borders[1]);

        return new MapVisualizer(this).draw(this.lower, this.upper);
    }
}

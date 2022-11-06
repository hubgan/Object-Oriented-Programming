package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final ArrayList<AbstractWorldMapElement> elements = new ArrayList<>();
    protected Vector2d lower = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upper = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (AbstractWorldMapElement element: this.elements) {
            if (element.isAt(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (AbstractWorldMapElement element: this.elements) {
            if (element.isAt(position)) {
                return element;
            }
        }

        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.lower, this.upper);
    }
}

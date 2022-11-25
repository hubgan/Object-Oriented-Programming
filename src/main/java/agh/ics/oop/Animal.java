package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
        addObserver((IPositionChangeObserver) this.map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        addObserver((IPositionChangeObserver) this.map);
    }

    public String toString() {
        return switch(this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                Vector2d oldPosition = this.position;
                if (this.map.canMoveTo(newPosition)) {
                    this.position = newPosition;
                    positionChange(oldPosition);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUnitVector());
                Vector2d oldPosition = this.position;
                if (this.map.canMoveTo(newPosition)) {
                    this.position = newPosition;
                    positionChange(oldPosition);
                }
            }
            default -> {
            }
        }
    }

    void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    void positionChange(Vector2d position) {
        for (IPositionChangeObserver observer: this.observers) {
            observer.positionChanged(position, this.position);
        }
    }

    @Override
    public String getImageResource() {
        return switch (this.orientation) {
            case NORTH -> "/src/main/resources/up.png";
            case SOUTH -> "/src/main/resources/down.png";
            case WEST -> "/src/main/resources/left.png";
            case EAST -> "/src/main/resources/right.png";
        };
    }

    @Override
    public String getLabel() {
        return "Z " + this.getPosition().toString();
    }
}

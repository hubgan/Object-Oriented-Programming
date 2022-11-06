package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
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
                Vector2d addAnimal = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(addAnimal)) {
                    this.position = addAnimal;
                }
            }
            case BACKWARD -> {
                Vector2d subtractAnimal = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(subtractAnimal)) {
                    this.position = subtractAnimal;
                }
            }
            default -> {
            }
        }
    }
}

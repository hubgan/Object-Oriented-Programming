package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public String toString() {
        return this.position + "," + this.orientation;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private boolean isInRange(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(4, 4));
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case FORWARD:
                Vector2d addAnimal = this.position.add(this.orientation.toUnitVector());
                if (isInRange(addAnimal)) {
                    this.position = addAnimal;
                }
                break;
            case BACKWARD:
                Vector2d subtractAnimal = this.position.subtract(this.orientation.toUnitVector());
                if (isInRange(subtractAnimal)) {
                    this.position = subtractAnimal;
                }
                break;
            default:
                break;
        }
    }
}

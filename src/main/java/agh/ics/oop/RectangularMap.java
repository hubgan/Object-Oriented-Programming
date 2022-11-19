package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    public RectangularMap(int width, int height) {
        this.lower = new Vector2d(0, 0);
        this.upper = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);

        return position.follows(lower) &&
                position.precedes(upper) &&
                !(element instanceof Animal);
    }

    @Override
    public Vector2d[] getMapBorders() {
        return new Vector2d[]{this.lower, this.upper};
    }
}

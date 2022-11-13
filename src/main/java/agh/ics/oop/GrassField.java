package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int lowerBorder;
    private final int upperBorder;

    public GrassField(int n) {
        this.lowerBorder = 0;
        this.upperBorder = (int) Math.ceil(Math.sqrt(n * 10));
        createRandomGrass(n);
    }

    public void createRandomGrass(int grassNumber) {
        Random random = new Random();
        int counter = 0;

        while (counter < grassNumber) {
            Vector2d grassPosition =
                    new Vector2d(random.nextInt(this.upperBorder - this.lowerBorder) + this.lowerBorder,
                            random.nextInt(this.upperBorder - this.lowerBorder) + this.lowerBorder);

            if (!isOccupied(grassPosition)) {
                counter += 1;
                this.elements.put(grassPosition, new Grass(grassPosition));
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);

        if (element instanceof Grass) {
            this.elements.remove(((Grass) element).getPosition());
            createRandomGrass(1);
        }

        return !(element instanceof Animal);
    }

    public String toString() {
        for (Vector2d element: this.elements.keySet()) {
            this.lower = this.lower.lowerLeft(element);
            this.upper = this.upper.upperRight(element);
        }

        return super.toString();
    }
}

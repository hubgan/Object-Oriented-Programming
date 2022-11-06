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
                this.elements.add(new Grass(grassPosition));
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);

        if (element instanceof Grass) {
            this.elements.remove(element);
            createRandomGrass(1);
        }

        return !(element instanceof Animal);
    }

    public String toString() {
        for (AbstractWorldMapElement element: this.elements) {
            this.lower = this.lower.lowerLeft(element.getPosition());
            this.upper = this.upper.upperRight(element.getPosition());
        }

        return super.toString();
    }
}

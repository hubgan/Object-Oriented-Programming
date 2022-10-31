package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    private final ArrayList<Animal> animals = new ArrayList<>();

    // Nie wykorzystuje sposoby podanego w zadaniu 10 z poprzedniego laboratorium, ponieważ funkcja objectAt
    // musi zwracac zwierze z danej pozycji, to stworzenie tablicy dwuwymiarowej z wartościami boolean i tak
    // wymagałaby posiadanie listy animals ze zwierzętami

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(this.width - 1, this.height - 1)) &&
                !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: this.animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }

        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }
}

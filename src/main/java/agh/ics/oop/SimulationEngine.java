package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;

        for (Vector2d position: positions) {
            Animal animal = new Animal(this.map, position);

            if (this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    @Override
    public void run() {
        int numberOfAnimals = this.animals.size();

        for (int i = 0; i < directions.length; i++) {
            animals.get(i % numberOfAnimals).move(directions[i]);
            System.out.println(this.map);
        }
    }
}

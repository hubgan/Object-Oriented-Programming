package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private App app = null;
    private int delay = 500;

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

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, App app, int delay) {
        this.directions = directions;
        this.map = map;
        this.delay = delay;
        this.app = app;

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
        if (this.app == null) {
            for (int i = 0; i < directions.length; i++) {
                animals.get(i % numberOfAnimals).move(directions[i]);
                System.out.println(this.map);
            }
        }
        else {
            try {
                Platform.runLater(app::renderGridPane);
                for (int i = 0; i < directions.length; i++) {
                    animals.get(i % numberOfAnimals).move(directions[i]);
                    Platform.runLater(app::renderGridPane);
                    Thread.sleep(this.delay);
                    System.out.println(this.map);
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}

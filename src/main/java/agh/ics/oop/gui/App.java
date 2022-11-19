package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    IWorldMap map;
    int cellWidth = 30;
    int cellHeight = 30;

    public void init() {
//        RectangularMap
//        try {
//            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
//            this.map = new RectangularMap(10, 5);
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//        } catch(IllegalArgumentException exception) {
//            exception.printStackTrace();
//            Platform.exit();
//        }
//        GrassField
        try {
            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, this.map, positions);
            engine.run();
        } catch(IllegalArgumentException exception) {
            exception.printStackTrace();
            Platform.exit();
        }
    }

    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Vector2d[] borders = this.map.getMapBorders();
        int leftX = borders[0].x;
        int bottomY = borders[0].y;
        int rightX = borders[1].x;
        int topY = borders[1].y;

        Label yxLabel = new Label("y/x");
        gridPane.add(yxLabel, 0, 0, 1, 1);
        gridPane.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));
        gridPane.getRowConstraints().add(new RowConstraints(this.cellHeight));
        GridPane.setHalignment(yxLabel, HPos.CENTER);

        for (int i = 1; i < rightX - leftX + 2; i++) {
            int value = leftX + i - 1;
            Label label = new Label(value + "");
            gridPane.add(label, i, 0, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < topY - bottomY + 2; i++) {
            int value = topY - i + 1;
            Label label = new Label(value + "");
            gridPane.add(label, 0, i, 1, 1);
            gridPane.getRowConstraints().add(new RowConstraints(this.cellHeight));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = 1; i < rightX - leftX + 2; i++) {
            for (int j = 1; j < topY - bottomY + 2; j++) {
                Label label;
                Object element = this.map.objectAt(new Vector2d(leftX + i - 1, topY - j + 1));

                if (element == null) {
                    label = new Label("");
                }
                else {
                    label = new Label(element.toString());
                }

                gridPane.add(label, i, j, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

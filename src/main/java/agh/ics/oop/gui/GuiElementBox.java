package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {

    VBox vbox;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        try {
            String filePath = new File("").getAbsolutePath();
            Image image = new Image(new FileInputStream(filePath.concat(element.getImageResource())));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            Label label = new Label(element.getLabel());

            this.vbox = new VBox(1);
            this.vbox.getChildren().addAll(imageView, label);
            this.vbox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Node getStyleableNode() {
        return this.vbox;
    }
}

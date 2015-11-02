package view;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class PickImage implements Clickable<Button> {
    private Button pickImage;
    private FileChooser fileChooser;

    public PickImage (Pane canvasBox, Turtle turtle) {
        fileChooser = initFileChooser();
        pickImage = new Button("Pick Image");
        pickImage.setOnAction(e -> this.imagePrompt(canvasBox, turtle));

    }

    private void imagePrompt (Pane canvasBox, Turtle turtle) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            canvasBox.getChildren().remove(turtle.getTurtleImage());
            turtle.setTurtleImage(image1);
            canvasBox.getChildren().add(turtle.getTurtleImage());
        }
    }

    private FileChooser initFileChooser () {
        FileChooser f = new FileChooser();
        f.setTitle("Open Image File");
        f.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
        return f;
    }

    @Override
    public Button getClickable () {
        return pickImage;
    }
}

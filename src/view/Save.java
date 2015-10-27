package view;

import java.util.Optional;
import engine.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;


public class Save extends Clickable<Button> {
    private Button save;

    public Save (Controller controller, TextArea t) {
        save = new Button("Save");
        save.setOnAction(e -> this.saveMethod(controller, t));
    }

    private void saveMethod (Controller myController, TextArea t) {
        TextInputDialog textDialog = new TextInputDialog();
        textDialog.setTitle("Program Name");
        textDialog.setHeaderText("Enter the name for your program");

        Optional<String> result = textDialog.showAndWait();
        // TODO: add catch for bad file name type
        result.ifPresent(name -> myController.onSave(t.getText(), name));
    }

    @Override
    Button getClickable () {
        return save;
    }
}

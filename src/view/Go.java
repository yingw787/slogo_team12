package view;

import engine.IController;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class Go extends Clickable<Button> {
    private Button go;

    public Go (TextArea t, IController controller) {
        go = new Button("Go");
        go.setOnAction(e -> controller.submit(t.getText(), "English"));
    }

    @Override
    Button getClickable () {
        return go;
    }

}

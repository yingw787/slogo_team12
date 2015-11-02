package view;

import engine.IController;
import javafx.scene.control.Button;


public class Reset extends Clickable<Button> {
    private Button reset;

    public Reset (IController controller) {
        reset = new Button("Reset");
        reset.setOnAction(e -> controller.reset());
    }

    @Override
    Button getClickable () {
        return reset;
    }
}

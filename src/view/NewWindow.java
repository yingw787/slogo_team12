package view;

import engine.Controller;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewWindow extends Clickable<Button>{
    private Button newWindow;
    
    public NewWindow(Controller controller){
        newWindow = new Button("New Window");
        newWindow.setOnAction(e -> controller.makeNewWindow(new Stage()));
    }

    @Override
    Button getClickable () {
        return newWindow;
    }
}

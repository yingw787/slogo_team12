package engine;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Backend;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private Backend myBackend;
	@Override
	public void start(Stage primaryStage) throws Exception {
		myGUI = new GUI(this, "English");
		myBackend = new Backend(this);
		
		
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(primaryStage);
		
		
		
	}
	
	
	

	
	

}

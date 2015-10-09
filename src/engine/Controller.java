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
		
		
		//discussed in lecture -- Observable!
		//sets up observable relationships
		//for making modular, for instace, history pane, canvas, both 
		//want to know when something has changed (something obervable)
		//then act everytime change

		
		myGUI = new GUI(this, "English");
		myBackend = new Backend(this);
		
		
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(primaryStage);
		
		
		
	}
	
	public void reset(){
		System.out.println("reset");
		//clear history, reset turtle, clear everythibg.
		//just make new Gui object and set it?
		
		
	}
	
	public void submit(){
		System.out.println("");
		//myGui.getText();
	}
	

	
	

	
	

}

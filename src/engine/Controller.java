package engine;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Backend;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private Backend myBackend;
	private Stage myStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		myStage = primaryStage;
		//discussed in lecture -- Observable!
		//sets up observable relationships
		//for making modular, for instace, history pane, canvas, both 
		//want to know when something has changed (something obervable)
		//then act everytime change

		
		myGUI = new GUI(this, "English");
		myBackend = new Backend(this);
		
		
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(myStage);
		
		
		
	}
	
	public void reset(){
		System.out.println("reset");
		
		myGUI = new GUI(this, "English");
		myGUI.setAndShowScene(myStage);
		//clear history, reset turtle, clear everythibg.
		//just make new Gui object and set it? decide what to do
		
		
	}
	
	public void submit(String stringFromGUI){
		System.out.println(stringFromGUI);
		
		//
		myGUI.addToHistory(stringFromGUI);
		
		
	}
	

	
	

	
	

}

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
//import model.Backend;
//import view.GUI;


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
		myGUI.addToHistory(stringFromGUI);
		
	}

	
	
	//ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
	public double getTurtleDirection() {
		//TODO returns angle (in degrees) from north, measured clockwise
		return 0.0;
	}
	
	public void setTurtleDirection(double angle) {
		//TODO sets turtle's direction
	}
	
	public double[] getTurtlePosition() {
		//TODO returns x,y position of turtle
		return new double[2];
	}
	
	public void setTurtlePosition(double[] newPos) {
		//TODO sets turtle's x,y position
	}
	
	public boolean isTurtlePenDown() {
		// TODO returns true if pen is down
		return true;
	}
	
	public void setIsTurtlePenDown(boolean penDown) {
		// sets turtle's "pen down" boolean
	}
	
	public boolean isTurtleShowing() {
		// TODO returns true if turtle is showing
		return true;
	}
	
	public void setIsTurtleShowing(boolean showing) {
		// TODO sets turtle's "hidden" boolean
	}
}

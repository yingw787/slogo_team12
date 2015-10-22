package engine;

import javafx.application.Application;
import javafx.stage.Stage;
import model.BackEndController;
import model.Backend;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private BackEndController myBackend;
	private Stage myStage;
	private String myLanguage = "English";
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

		
		myGUI = new GUI(this, myLanguage);
		myBackend = new BackEndController(this);
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(myStage);

		
		
		
	}
	
	public void reset(){
		System.out.println("reset");
		
		myGUI = new GUI(this, myLanguage);
		myGUI.setAndShowScene(myStage);
		myBackend = new BackEndController(this);
		//clear history, reset turtle, clear everythibg.
		//just make new Gui object and set it? decide what to do

		
		
	}
	
	public void submit(String stringFromGUI, String myLanguage){
		
		if (stringFromGUI.trim().length() > 0) {
		myGUI.addToHistory(stringFromGUI);
		myBackend.generateTurtleCommands(stringFromGUI, myLanguage);
		}
	}
	
	public void makeNewWindow(Stage s){
		
		Controller anotherController = new Controller();
		try {
			anotherController.start(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		return myGUI.getTurtlePosition();
		

	}
	
	public void setTurtlePosition(double[] newPos) {
		myGUI.updateTurtle(newPos);
		if(isTurtlePenDown()){
		myGUI.drawLine();
		}
		//sets turtle's x,y position
	}
	
	public boolean isTurtlePenDown() {
		// returns true if pen is down
		return myGUI.getPenBool();
	}
	
	public void setIsTurtlePenDown(boolean penDown) {
		myGUI.setTurtlePen(penDown);
		// sets turtle's "pen down" boolean
	}
	
	public boolean isTurtleShowing() {
		// TODO returns true if turtle is showing
		return myGUI.getTurtleVisible();
	}
	
	public void setIsTurtleShowing(boolean showing) {
		
		myGUI.setTurtleVisible(showing);
		//sets turtle's "hidden" boolean
	}
}

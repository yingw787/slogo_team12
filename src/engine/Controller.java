package engine;

import javafx.application.Application;
import javafx.stage.Stage;
import model.BackEndController;
import view.GUI;

public class Controller extends Application {

	private GUI myGUI;
	private BackEndController myBackend;
	private Stage myStage;
	private String myLanguage = "English";
	private int myActiveTurtle;
	
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
		myActiveTurtle = 1;
		//init gui to set up everything, call this part of it last
		myGUI.setAndShowScene(myStage);

		
		
		
	}
	
	public void reset(){
		System.out.println("reset");
		
		myGUI = new GUI(this, myLanguage);
		myGUI.setAndShowScene(myStage);
		myBackend = new BackEndController(this);
		myActiveTurtle = 1;

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
	
	public int getActiveTurtleID() {
		return myActiveTurtle;
	}
	
	public void setActiveTurtleID(int newID) {
		myActiveTurtle = newID;
	}

	
	//ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
	//refactor these to only get and set values for myActiveTurtle id number
	public void clear() {
		myGUI.clearLines();
	}
	
	public int getNumTurtles() {
		//TODO get the max turtle ID aka number of turtles
		return 1;
	}
	
	public double getTurtleDirection() {
		
	    return myGUI.getTurtleDirection();
	}
	
	public void setTurtleDirection(double angle) {
	    myGUI.setTurtleDirection(angle);
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

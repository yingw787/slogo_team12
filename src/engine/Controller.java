package engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BackEndController;
import view.GUI;


public class Controller extends Application implements IController{

    private GUI myGUI;
    private BackEndController myBackend;
    private Stage myStage;
    private String myLanguage = "English";
    private IntegerProperty myActiveTurtle;
    private Map<String, Double> myVariablesMap;

    public Controller () {
        myVariablesMap = new HashMap<String, Double>();
    }

   
	@Override
    public void start (Stage primaryStage) throws Exception {

        myStage = primaryStage;
     
        initializeActiveTurtleProperty();
        reset();

    }

  
    @Override
	public void reset () {
        initializeActiveTurtleProperty();
        myGUI = new GUI(new ControllerWrapperFront(this), myLanguage, myActiveTurtle);
        myBackend = new BackEndController(new ControllerWrapperBack(this));
        myGUI.setAndShowScene(myStage);
      

        // clear history, reset turtle, clear everythibg.
        // just make new Gui object and set it? decide what to do

    }

    
    @Override
	public void submit (String stringFromGUI, String myLanguage) {

        if (stringFromGUI.trim().length() > 0) {
            myGUI.addToHistory(stringFromGUI);
            myBackend.generateTurtleCommands(stringFromGUI, myLanguage);
            myGUI.updateVariablesMap();
        }
    }

 
    @Override
	public void makeNewWindow (Stage s) {

        IController anotherController = new Controller();
        try {
            anotherController.start(s);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Override
	public int getActiveTurtleID () {
        return myActiveTurtle.get();
    }

  
    @Override
	public void setActiveTurtleID (int newID) {
        myActiveTurtle.set(newID);

    }

    @Override
	public Map<String, Double> getUnmodifiableVariablesMap () {
        return Collections.unmodifiableMap(myVariablesMap);
    }

  
    @Override
	public Map<String, Double> getVariablesMap () {
        return myVariablesMap;
    }

  
    @Override
	public void setVariablesMap (Map<String, Double> variablesMap) {
        myVariablesMap = variablesMap;
    }

 
    @Override
	public void changeVariableName (String oldName, String newName) {
        double value = myVariablesMap.get(oldName);
        myVariablesMap.remove(oldName);
        myVariablesMap.put(newName, value);
    }

  
    @Override
	public void changeVariableValue (String key, String newValue) {
        myVariablesMap.put(key, Double.parseDouble(newValue));
    }

    // ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
  
    @Override
	public void setBackgroundColor (int index) {
        myGUI.setBackgroundColor(index);
    }


    @Override
	public void setPenColor (int index) {
        myGUI.setTurtlePenColor(index);
    }

 
    @Override
	public void setPenSize (double pixels) {
        // TODO sets pen stroke size to pixels value
    }

  
    @Override
	public void setShape (int index) {
        // TODO sets shape of turtle to that represented by index
    }

 
    @Override
	public void setPalette (int index, Color newColor) {
        myGUI.setPalette(index, newColor);
    }

   
    @Override
	public int getPenColor () {
        return myGUI.getTurtlePenIndex();
    }

  
    @Override
	public int getShape () {
        // TODO returns the index of the active turtle's shape
        return 0;
    }

   
    @Override
	public void makeStamp () {
        // TODO makes a stamp of the active turtle
    }

  
    @Override
	public int getNumStamps () {
        // TODO returns number of stamps on the canvas
        return 0;
    }

  
    @Override
	public void clearStamps () {
        // TODO clears all stamps off the canvas (similar to clearLines())
    }

  
    @Override
	public int getNumTurtles () {
        // TODO get the max turtle ID aka number of turtles
        return myGUI.getNumTurtles();
    }

  
    @Override
	public void clear () {
        myGUI.clearLines();
    }

  
    @Override
	public double getTurtleDirection () {

        return myGUI.getTurtleDirection();
    }


    @Override
	public void setTurtleDirection (double angle) {

        myGUI.setTurtleDirection(angle);

    }

 
    @Override
	public double[] getTurtlePosition () {
        return myGUI.getTurtlePosition();

    }

 
    @Override
	public void setTurtlePosition (double[] newPos) {
        myGUI.updateTurtle(newPos, myActiveTurtle.get());
       
    }


    @Override
	public boolean isTurtlePenDown () {
        // returns true if pen is down
        return myGUI.getPenBool();
    }

 
    @Override
	public void setIsTurtlePenDown (boolean penDown) {
        myGUI.setTurtlePen(penDown);
        // sets turtle's "pen down" boolean
    }

 
    @Override
	public boolean isTurtleShowing () {
        return myGUI.getTurtleVisible();
    }


    @Override
	public void setIsTurtleShowing (boolean showing) {

        myGUI.setTurtleVisible(showing);
        // sets turtle's "hidden" boolean
    }

    private void initializeActiveTurtleProperty () {
        myActiveTurtle = new SimpleIntegerProperty(1);
    }

 
    @Override
	public void onSave (String input, String programTitle) {
        myBackend.saveProgram(input, programTitle);
    }

}

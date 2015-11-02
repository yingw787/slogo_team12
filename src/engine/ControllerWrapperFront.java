// This entire file is part of my masterpiece.
// SHARRIN MANOR

package engine;

import java.util.Map;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerWrapperFront implements IController {

	private IController myController;
	
	public ControllerWrapperFront(Controller control){
		myController = control;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//no permission
	}

	@Override
	public void reset() {
		myController.reset();
	}

	@Override
	public void submit(String stringFromGUI, String myLanguage) {
		myController.reset();
		
	}

	@Override
	public void makeNewWindow(Stage s) {
		myController.makeNewWindow(s);
		
	}

	@Override
	public int getActiveTurtleID() {
		// no permission
		return 0;
	}

	@Override
	public void setActiveTurtleID(int newID) {
		// no permission
		
	}

	@Override
	public Map<String, Double> getUnmodifiableVariablesMap() {
		return myController.getUnmodifiableVariablesMap();
	}

	@Override
	public Map<String, Double> getVariablesMap() {
		// no permission
		return null;
	}

	@Override
	public void setVariablesMap(Map<String, Double> variablesMap) {
		// no permission
		
	}

	@Override
	public void changeVariableName(String oldName, String newName) {
		 myController.changeVariableName(oldName, newName);
		
	}

	@Override
	public void changeVariableValue(String key, String newValue) {
		myController.changeVariableValue(key, newValue);
	}

	@Override
	public void setBackgroundColor(int index) {
		//no permission
	}

	@Override
	public void setPenColor(int index) {
		//no permission
		
	}

	@Override
	public void setPenSize(double pixels) {
		// no permission
		
	}

	@Override
	public void setShape(int index) {
		//no permission
		
	}

	@Override
	public void setPalette(int index, Color newColor) {
		// no permission
		
	}

	@Override
	public int getPenColor() {
		// no permission
		return 0;
	}

	@Override
	public int getShape() {
		// no permission
		return 0;
	}

	@Override
	public void makeStamp() {
		// no permission
		
	}

	@Override
	public int getNumStamps() {
		// no permission
		return 0;
	}

	@Override
	public void clearStamps() {
		// no permission
		
	}

	@Override
	public int getNumTurtles() {
		// no permission
		return 0;
	}

	@Override
	public void clear() {
		// no permission
		
	}

	@Override
	public double getTurtleDirection() {
		// no permission
		return 0;
	}

	@Override
	public void setTurtleDirection(double angle) {
		//no permission
		
	}

	@Override
	public double[] getTurtlePosition() {
		// no permission
		return null;
	}

	@Override
	public void setTurtlePosition(double[] newPos) {
		// no permission
		
	}

	@Override
	public boolean isTurtlePenDown() {
		// no permission
		return false;
	}

	@Override
	public void setIsTurtlePenDown(boolean penDown) {
		// no permission
		
	}

	@Override
	public boolean isTurtleShowing() {
		//no permission
		return false;
	}

	@Override
	public void setIsTurtleShowing(boolean showing) {
		// no permission
		
	}

	@Override
	public void onSave(String input, String programTitle) {
		myController.onSave(input, programTitle);
		
	}

}

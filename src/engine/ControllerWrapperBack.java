// This entire file is part of my masterpiece.
// SHARRIN MANOR
package engine;

import java.util.Map;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerWrapperBack implements IController {

	//wrapper so back end can only call some methods
	
	private IController myController;
	public ControllerWrapperBack(IController control){
		
		control = myController;
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reset() {
		
		//no permission
		
	}
	@Override
	public void submit(String stringFromGUI, String myLanguage) {
		//no permission
	}
	@Override
	public void makeNewWindow(Stage s) {
		//no permission
	}
	@Override
	public int getActiveTurtleID() {
		return myController.getActiveTurtleID();
	}
	@Override
	public void setActiveTurtleID(int newID) {
		myController.setActiveTurtleID(newID);
		
	}
	@Override
	public Map<String, Double> getUnmodifiableVariablesMap() {
		return myController.getUnmodifiableVariablesMap();
	}
	@Override
	public Map<String, Double> getVariablesMap() {
		return myController.getVariablesMap();
	}
	@Override
	public void setVariablesMap(Map<String, Double> variablesMap) {
		myController.setVariablesMap(variablesMap);
		
	}
	@Override
	public void changeVariableName(String oldName, String newName) {
		//no permission
	}
	@Override
	public void changeVariableValue(String key, String newValue) {
		// no permission
	}
	@Override
	public void setBackgroundColor(int index) {
		myController.setBackgroundColor(index);
		
	}
	@Override
	public void setPenColor(int index) {
		myController.setPenColor(index);
		
	}
	@Override
	public void setPenSize(double pixels) {
		myController.setPenSize(pixels);
		
	}
	@Override
	public void setShape(int index) {
		myController.setShape(index);
		
	}
	@Override
	public void setPalette(int index, Color newColor) {
		myController.setPalette(index, newColor);
		
	}
	@Override
	public int getPenColor() {
		return myController.getPenColor();
	}
	@Override
	public int getShape() {
		return myController.getShape();
	}
	@Override
	public void makeStamp() {
		myController.makeStamp();
		
	}
	@Override
	public int getNumStamps() {
		return myController.getNumStamps();
	}
	@Override
	public void clearStamps() {
		myController.clearStamps();
		
	}
	@Override
	public int getNumTurtles() {
		return myController.getNumTurtles();
	}
	@Override
	public void clear() {
		myController.clear();
		
	}
	@Override
	public double getTurtleDirection() {
		return myController.getTurtleDirection();
	}
	@Override
	public void setTurtleDirection(double angle) {
		myController.setTurtleDirection(angle);
		
	}
	@Override
	public double[] getTurtlePosition() {
		return myController.getTurtlePosition();
	}
	@Override
	public void setTurtlePosition(double[] newPos) {
		myController.setTurtlePosition(newPos);
		
	}
	@Override
	public boolean isTurtlePenDown() {
		// TODO Auto-generated method stub
		return myController.isTurtlePenDown();
	}
	@Override
	public void setIsTurtlePenDown(boolean penDown) {
		myController.setIsTurtlePenDown(penDown);
		
	}
	@Override
	public boolean isTurtleShowing() {
		return myController.isTurtleShowing();
	}
	@Override
	public void setIsTurtleShowing(boolean showing) {
		myController.setIsTurtleShowing(showing);
		
	}
	@Override
	public void onSave(String input, String programTitle) {
		//no permission
	}
	
	
}

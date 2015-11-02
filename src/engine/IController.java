package engine;

import java.util.Map;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public interface IController {

	void start(Stage primaryStage) throws Exception;

	void reset();

	void submit(String stringFromGUI, String myLanguage);

	void makeNewWindow(Stage s);

	int getActiveTurtleID();

	void setActiveTurtleID(int newID);

	Map<String, Double> getUnmodifiableVariablesMap();

	Map<String, Double> getVariablesMap();

	void setVariablesMap(Map<String, Double> variablesMap);

	void changeVariableName(String oldName, String newName);

	void changeVariableValue(String key, String newValue);

	// ELIZABETH'S ADDITIONS FOR CONNECTING TURTLE TO COMMANDS
	void setBackgroundColor(int index);

	void setPenColor(int index);

	void setPenSize(double pixels);

	void setShape(int index);

	void setPalette(int index, Color newColor);

	int getPenColor();

	int getShape();

	void makeStamp();

	int getNumStamps();

	void clearStamps();

	int getNumTurtles();

	void clear();

	double getTurtleDirection();

	void setTurtleDirection(double angle);

	double[] getTurtlePosition();

	void setTurtlePosition(double[] newPos);

	boolean isTurtlePenDown();

	void setIsTurtlePenDown(boolean penDown);

	boolean isTurtleShowing();

	void setIsTurtleShowing(boolean showing);

	void onSave(String input, String programTitle);

}
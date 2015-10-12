package commands;

import java.util.List;

import engine.Controller;

public abstract class Command {
	private String myValue;
	private List<Command> myParameters;
	private Controller myController;
	
	public Command() {
		//do nothing
	}
	
	public Command(String expression, List<Command> params) {
		myValue = expression;
		myParameters = params;
	}
	
	public Command(Controller controller, String expression, List<Command> params) {
		myValue = expression;
		myParameters = params;
		myController = controller;
	}
	
	public String getValue() {
		return myValue;
	}
	
	public List<Command> getParameters() {
		return myParameters;
	}
	
	public Controller getController() {
		return myController;
	}
	
	public void setValue(String expression) {
		this.myValue = expression;
	}
	
	public void setParameters(List<Command> commandList) {
		this.myParameters = commandList;
	}
	
	public void setController(Controller controller) {
		this.myController = controller;
	}
	
	public abstract String getCommandType();
	
	public abstract int getNumParameters();
	
	public abstract int returnInt();
	
	public abstract void execute();
	
	protected int convertRadiansToDegrees(double radianValue) {
		return (int)(radianValue*(180/Math.PI));
	}
	
	protected int calculateDistance(int[] startPos, int[] endPos) {
		return (int)Math.sqrt(Math.pow(endPos[0]-startPos[0], 2) + Math.pow(endPos[1]-startPos[1], 2));
	}
}

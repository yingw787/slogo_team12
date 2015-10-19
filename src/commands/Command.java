package commands;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

import engine.Controller;
import model.TurtleStatus;

public abstract class Command {
	private String myExpression;
	private List<Command> myParameters;
	private Controller myController;
	private Map<String,Double> myVariables;
	private Map<String,UserCommand> myUserCommands;
	private Queue<TurtleStatus> myTurtleUpdates;
	
	public Command() {
		//do nothing
	}
	
	public Command(Map<String,Double> variables) {
		myVariables = variables;
	}
	
	public Command(String expression, List<Command> params) {
		myExpression = expression;
		myParameters = params;
	}
	
	public Command(Controller controller, String expression, List<Command> params) {
		myExpression = expression;
		myParameters = params;
		myController = controller;
	}
	
	public abstract String getCommandType();
	
	public abstract int getNumParameters();
	
	public abstract double returnDoubleValue();
	
	public abstract void execute();
	
	public String getExpression() {
		return myExpression;
	}
	
	public List<Command> getParameters() {
		return myParameters;
	}
	
	public Controller getController() {
		return myController;
	}
	
	public Map<String,Double> getVariables() {
		return myVariables;
	}
	
	public Queue<TurtleStatus> getTurtleUpdates() {
		return myTurtleUpdates;
	}
	
	public Map<String,UserCommand> getUserCommands() {
		return myUserCommands;
	}
	
	public void setValue(String expression) {
		this.myExpression = expression;
	}
	
	public void setParameters(List<Command> commandList) {
		this.myParameters = commandList;
	}
	
	public void setController(Controller controller) {
		this.myController = controller;
	}
	
	public void setVariableMap(Map<String,Double> variables) {
		this.myVariables = variables;
	}
	
	public void setTurtleUpdates(Queue<TurtleStatus> turtleUpdates) {
		this.myTurtleUpdates = turtleUpdates;
	}
	
	public void setUserCommands(Map<String,UserCommand> userCommands) {
		this.myUserCommands = userCommands;
	}
	
	protected void addVariable(String variableName, Double value) {
		myVariables.put(variableName, value);
	}
	
	protected void removeVariable(String variableName) {
		myVariables.remove(variableName);
	}
	
	protected void addUserCommand(UserCommand newCommand) {
		myUserCommands.put(newCommand.getCommandName(), newCommand);
	}
	
	protected UserCommand getUserCommand(String commandName) {
		return myUserCommands.get(commandName);
	}
	
	protected Command getParameter(int index) {
		return myParameters.get(index);
	}
	
	protected double getParameterDoubleValue(int index) {
		Command argument = myParameters.get(index);
		return argument.returnDoubleValue();
	}
	
	protected String getParameterExpression(int index) {
		Command argument = myParameters.get(index);
		return argument.getExpression();
	}
	
	protected double convertRadiansToDegrees(double radianValue) {
		return radianValue*(180/Math.PI);
	}
	
	protected double convertDegreesToRadians(double degreesValue) {
		return degreesValue/(180/Math.PI);
	}
	
	protected double calculateDistance(double[] startPos, double[] endPos) {
		return Math.sqrt(Math.pow(endPos[0]-startPos[0], 2) + Math.pow(endPos[1]-startPos[1], 2));
	}
	
	protected double performBinaryDoubleOp(BiFunction<Double, Double, Double> func) {
		return func.apply(myParameters.get(0).returnDoubleValue(), myParameters.get(1).returnDoubleValue());
	}
	
	protected double performUnaryDoubleOp(Function<Double, Double> func) {
		return func.apply(myParameters.get(0).returnDoubleValue());
	}
	
	protected double performUnaryTrigOp(Function<Double, Double> trigFunc) {
		return convertRadiansToDegrees(trigFunc.apply(convertDegreesToRadians(myParameters.get(0).returnDoubleValue())));
	}
	
	protected boolean performBinaryBooleanOp(BiFunction<Double, Double, Boolean> func) {
		return func.apply(myParameters.get(0).returnDoubleValue(), myParameters.get(1).returnDoubleValue());
	}
	
	protected boolean bitToBoolean(int bit) {
		return bit == 1;
	}
	
	protected int booleanToBit(boolean bool) {
		return bool ? 1 : 0;
	}
	
	protected void addUpdatedTurtleStatus() {
		myTurtleUpdates.add(new TurtleStatus(myController));
	}
}

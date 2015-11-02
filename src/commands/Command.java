package commands;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import engine.IController;
import exceptions.NotEnoughParametersException;
import model.BackEndProperties;
import model.TurtleStatus;

public abstract class Command {
	private String myExpression;
	private List<Command> myParameters;
	private IController myController;
	private Map<String,Double> myVariables;
	private Map<String,UserCommand> myUserCommands;
	private List<Integer> myActiveTurtles;
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
	
	public Command(IController controller, String expression, List<Command> params) {
		myExpression = expression;
		myParameters = params;
		myController = controller;
	}
	
	public abstract String getCommandType();
	
	public abstract int getNumParameters();
	
	protected abstract double returnDoubleValue();
	
	public abstract void execute();
	
	public void executeCommandOverActiveTurtles() throws NotEnoughParametersException {
		if (myActiveTurtles.size() == 0) {
			myActiveTurtles.add(1);
		}
		if(this.myParameters.size() == 0){
			throw new NotEnoughParametersException(); 
		}
		
		
		executeCommandOverMultipleTurtles(myActiveTurtles);
	}
	
	public void executeCommandOverMultipleTurtles(List<Integer> turtleList) {
		if (!this.getCommandType().equals(BackEndProperties.MULTIPLE_TURTLE_COMMAND)) {
			int startID = myController.getActiveTurtleID();
			turtleList.forEach(i -> setActiveTurtleAndExecute(i));
			myController.setActiveTurtleID(startID);
		} else {
			this.executeNestedCommands();
		}
	}

	private void setActiveTurtleAndExecute(Integer i) {
		myController.setActiveTurtleID(i);
		this.executeNestedCommands();
	}
	
	/**
	 * Executes a command by recursively checking for nested commands and executing those first
	 */
	protected Command executeNestedCommands() {
		if (this.getNumParameters() == 0 || this.shouldNotExecuteNestedCommands()) {
			return executeCommand();
		} else {
			this.getParameters().stream()
				.map(c -> c.executeNestedCommands())
				.collect(Collectors.toList());
			return executeCommand();
		}
	}
	
	private boolean shouldNotExecuteNestedCommands() {
		return this.getCommandType().equals(BackEndProperties.SPECIAL_FORM) || 
				this.getCommandType().equals(BackEndProperties.MULTIPLE_TURTLE_COMMAND);
	}
	
	private Command executeCommand() {
		this.execute();
		return this;
	}
	
	public String getExpression() {
		return myExpression;
	}
	
	public List<Command> getParameters() {
		return myParameters;
	}
	
	public IController getController() {
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
	
	public List<Integer> getActiveTurtles() {
		return myActiveTurtles;
	}
	
	public void setValue(String expression) {
		this.myExpression = expression;
	}
	
	public void setParameters(List<Command> commandList) {
		this.myParameters = commandList;
	}
	
	public void setController(IController controller) {
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
	
	public void setActiveTurtles(List<Integer> activeTurtles) {
		this.myActiveTurtles = activeTurtles;
	}
	
	protected void repopulateActiveTurtles(List<Integer> newActiveTurtles) {
		this.myActiveTurtles.clear();
		this.myActiveTurtles.addAll(newActiveTurtles);
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
	
	protected void removeUserCommand(String commandName) {
		myUserCommands.remove(commandName);
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
		return Math.toDegrees(trigFunc.apply(Math.toRadians(myParameters.get(0).returnDoubleValue())));
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

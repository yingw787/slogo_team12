package commands;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
	
	public abstract double returnDoubleValue();
	
	public abstract void execute();
	
	protected double getParameterValue() {
		Command argument = myParameters.get(0);
		return argument.returnDoubleValue();
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
}

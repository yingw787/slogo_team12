package commands;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class MathOperation extends Command {

	public MathOperation() {
		//do nothing
	}

	public MathOperation(String expression, List<Command> commandList) {
		super(expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return "MathOperation";
	}

	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();
	
	protected double performBinaryOp(BiFunction<Double, Double, Double> func) {
		List<Command> parameters = super.getParameters();
		return func.apply(parameters.get(0).returnDoubleValue(), parameters.get(1).returnDoubleValue());
	}
	
	protected double performUnaryOp(Function<Double, Double> func) {
		List<Command> parameters = super.getParameters();
		return func.apply(parameters.get(0).returnDoubleValue());
	}
	
	protected double performUnaryTrigOp(Function<Double, Double> trigFunc) {
		List<Command> parameters = super.getParameters();
		return convertRadiansToDegrees(trigFunc.apply(convertDegreesToRadians(parameters.get(0).returnDoubleValue())));
	}

}

package commands;

import java.util.List;

public class GreaterThan extends BooleanOperation {

	public GreaterThan() {
		super();
	}
	
	public GreaterThan(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return booleanToBit(performBinaryBooleanOp((a,b) -> a > b));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

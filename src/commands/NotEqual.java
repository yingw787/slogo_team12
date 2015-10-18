package commands;

import java.util.List;

public class NotEqual extends BooleanOperation {

	public NotEqual() {
		super();
	}
	
	public NotEqual(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return booleanToBit(performBinaryBooleanOp((a,b) -> a != b));
	}
}

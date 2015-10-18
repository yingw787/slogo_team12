package commands;

import java.util.List;

public class Or extends BooleanOperation {

	public Or() {
		super();
	}
	
	public Or(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return booleanToBit(performBinaryBooleanOp((a,b) -> bitToBoolean(a.intValue()) || bitToBoolean(b.intValue())));
	}
}

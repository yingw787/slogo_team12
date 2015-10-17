package commands;

import java.util.List;

public class And extends BooleanOperation {

	public And() {
		super();
	}
	
	public And(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return booleanToBit(performBinaryBooleanOp((a,b) -> bitToBoolean(a.intValue()) && bitToBoolean(b.intValue())));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

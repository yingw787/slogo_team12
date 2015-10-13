package commands;

import java.util.List;

public class Sum extends MathOperation {

	public Sum() {
		super();
	}
	
	public Sum(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return performBinaryDoubleOp((a, b) -> a + b);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}

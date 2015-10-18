package commands;

import java.util.List;

public class Remainder extends MathOperation {

	public Remainder() {
		super();
	}
	
	public Remainder(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return performBinaryDoubleOp((a, b) -> a % b);
	}
}

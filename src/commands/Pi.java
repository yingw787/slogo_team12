package commands;

import java.util.List;

public class Pi extends MathOperation {

	public Pi() {
		super();
	}
	
	public Pi(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 0;
	}

	@Override
	public double returnDoubleValue() {
		return Math.PI;
	}
}

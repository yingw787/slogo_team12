package commands;

import java.util.List;

public class Sine extends MathOperation {

	public Sine() {
		super();
	}
	
	public Sine(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryTrigOp(a -> Math.sin(a));
	}
}

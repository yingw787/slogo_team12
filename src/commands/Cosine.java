package commands;

import java.util.List;

public class Cosine extends MathOperation {

	public Cosine() {
		super();
	}
	
	public Cosine(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryTrigOp(a -> Math.cos(a));
	}
}

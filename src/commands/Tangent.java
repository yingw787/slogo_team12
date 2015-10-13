package commands;

import java.util.List;

public class Tangent extends MathOperation {

	public Tangent() {
		super();
	}
	
	public Tangent(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryTrigOp(a -> Math.tan(a));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

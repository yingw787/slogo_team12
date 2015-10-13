package commands;

import java.util.List;

public class Minus extends MathOperation {

	public Minus() {
		super();
	}
	
	public Minus(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryDoubleOp(a -> -1 * a);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

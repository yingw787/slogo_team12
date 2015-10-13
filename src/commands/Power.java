package commands;

import java.util.List;

public class Power extends MathOperation {

	public Power() {
		super();
	}
	
	public Power(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return performBinaryDoubleOp((a,b) -> Math.pow(a, b));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

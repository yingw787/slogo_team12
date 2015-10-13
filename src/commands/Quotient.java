package commands;

import java.util.List;

public class Quotient extends MathOperation {

	public Quotient() {
		super();
	}
	
	public Quotient(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		List<Command> parameters = super.getParameters();
		return parameters.get(0).returnDoubleValue() / parameters.get(1).returnDoubleValue();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}

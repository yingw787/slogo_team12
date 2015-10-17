package commands;

import java.util.List;

public class Product extends MathOperation {

	public Product() {
		super();
	}
	
	public Product(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		return performBinaryDoubleOp((a, b) -> a * b);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}

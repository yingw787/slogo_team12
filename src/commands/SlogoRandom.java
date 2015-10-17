package commands;

import java.util.List;
import java.util.Random;

public class SlogoRandom extends MathOperation {
	private Random myRandom;

	public SlogoRandom() {
		super();
		myRandom = new Random();
	}
	
	public SlogoRandom(String expression, List<Command> parameters) {
		super(expression, parameters);
		myRandom = new Random();
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryDoubleOp(a -> (double)myRandom.nextInt(a.intValue()));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

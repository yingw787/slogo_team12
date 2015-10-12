package commands;

import java.util.List;

public class NaturalLog extends MathOperation {
	
	public NaturalLog() {
		super();
	}
	
	public NaturalLog(String expression, List<Command> parameters) {
		super(expression, parameters);
	}

	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryOp(a -> Math.log(a));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}

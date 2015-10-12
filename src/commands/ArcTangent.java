package commands;

import java.util.List;

public class ArcTangent extends MathOperation {

	public ArcTangent() {
		super();
	}
	
	public ArcTangent(String expression, List<Command> parameters) {
		super(expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 1;
	}

	@Override
	public double returnDoubleValue() {
		return performUnaryTrigOp(a -> Math.atan(a));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}

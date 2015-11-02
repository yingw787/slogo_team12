package commands;

import java.util.List;

import engine.IController;

public class SetPosition extends TurtleCommand {

	public SetPosition() {
		super();
	}
	
	public SetPosition(IController controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public double returnDoubleValue() {
		double[] startPos = super.getController().getTurtlePosition();
		double[] endPos = calculateNewPosition();
		return calculateDistance(startPos, endPos);
	}

	@Override
	public void execute() {
		double[] newPos = calculateNewPosition();
		super.getController().setTurtlePosition(newPos);
	}
	
	private double[] calculateNewPosition() {
		List<Command> parameters = this.getParameters();
		double[] newPos = { parameters.get(0).returnDoubleValue() , parameters.get(1).returnDoubleValue() };
		return newPos;
	}

}

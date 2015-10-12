package commands;

import java.util.List;

import engine.Controller;

public class SetPosition extends TurtleCommand {

	public SetPosition() {
		super();
	}
	
	public SetPosition(Controller controller, String expression, List<Command> parameters) {
		super(controller, expression, parameters);
	}
	
	@Override
	public int getNumParameters() {
		return 2;
	}

	@Override
	public int returnInt() {
		int[] startPos = super.getController().getTurtlePosition();
		int[] endPos = calculateNewPosition();
		return calculateDistance(startPos, endPos);
	}

	@Override
	public void execute() {
		int[] newPos = calculateNewPosition();
		super.getController().setTurtlePosition(newPos);
	}
	
	private int[] calculateNewPosition() {
		List<Command> parameters = this.getParameters();
		int[] newPos = { parameters.get(0).returnInt() , parameters.get(1).returnInt() };
		return newPos;
	}

}

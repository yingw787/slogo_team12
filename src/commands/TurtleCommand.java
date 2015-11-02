package commands;

import java.util.List;

import engine.IController;
import model.BackEndProperties;

public abstract class TurtleCommand extends Command {

	public TurtleCommand() {
		//do nothing
	}
	
	public TurtleCommand(IController controller, String expression, List<Command> commandList) {
		super(controller, expression, commandList);
	}
	
	@Override
	public String getCommandType() {
		return BackEndProperties.TURTLE_COMMAND;
	}
	
	@Override
	public abstract int getNumParameters();

	@Override
	public abstract double returnDoubleValue();

	@Override
	public abstract void execute();
	
	protected void moveTurtleForwardBackward(int direction) {
		double distance = returnDoubleValue();
		double turtleDirection = getTurtleDirection(direction);
		double[] startPos = super.getController().getTurtlePosition();
		double[] newPos = calculateToPosition(distance, turtleDirection, startPos);
		super.getController().setTurtlePosition(newPos);
		addUpdatedTurtleStatus();
	}

	/**
	 * Changes direction of turtle's movement depending on quadrant of turtleDirection
	 */
	private double[] calculateToPosition(double distance, double turtleDirection, double[] startPos) {
		double newX = startPos[0] + distance * Math.cos(turtleDirection);
		double newY = startPos[1] - distance * Math.sin(turtleDirection);
		double[] newPos = { newX, newY };
		return newPos;
	}

	private double getTurtleDirection(int direction) {
		double turtleDirection = super.getController().getTurtleDirection();
		if (direction == -1) {
			turtleDirection += 180;
		}
		turtleDirection = Math.toRadians(turtleDirection);
		return turtleDirection;
	}

}

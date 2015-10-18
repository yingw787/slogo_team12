package commands;

import java.util.List;

import engine.Controller;
import model.BackEndProperties;
import model.TurtleStatus;

public abstract class TurtleCommand extends Command {

	public TurtleCommand() {
		//do nothing
	}
	
	public TurtleCommand(Controller controller, String expression, List<Command> commandList) {
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
		double turtleDirection = super.getController().getTurtleDirection();
		double[] startPos = super.getController().getTurtlePosition();
		double newX = startPos[0] + direction * distance * Math.cos(turtleDirection);
		double newY = startPos[1] + direction * distance * Math.sin(turtleDirection);
		double[] newPos = { newX, newY };
		super.getController().setTurtlePosition(newPos);
		addUpdatedTurtleStatus();
	}

}

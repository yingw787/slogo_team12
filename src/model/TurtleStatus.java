package model;

import engine.IController;

public class TurtleStatus {
	
	private double[] turtlePosition;
	private double turtleDirection;
	private boolean isPenDown;
	private boolean isTurtleShowing;

	public TurtleStatus(IController controller) {
		turtlePosition = controller.getTurtlePosition();
		turtleDirection = controller.getTurtleDirection();
		isPenDown = controller.isTurtlePenDown();
		isTurtleShowing = controller.isTurtleShowing();
	}

	public double[] getTurtlePosition() {
		return turtlePosition;
	}

	public void setTurtlePosition(double[] turtlePosition) {
		this.turtlePosition = turtlePosition;
	}

	public double getTurtleDirection() {
		return turtleDirection;
	}

	public void setTurtleDirection(double turtleDirection) {
		this.turtleDirection = turtleDirection;
	}

	public boolean isPenDown() {
		return isPenDown;
	}

	public void setPenDown(boolean isPenDown) {
		this.isPenDown = isPenDown;
	}

	public boolean isTurtleShowing() {
		return isTurtleShowing;
	}

	public void setTurtleShowing(boolean isTurtleShowing) {
		this.isTurtleShowing = isTurtleShowing;
	}

}

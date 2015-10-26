package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Turtle {
	private int turtleID;
	private double direction;
	private double CANVAS_WIDTH;
	private double CANVAS_HEIGHT;
	private double pastXPos;
	private double pastYPos;
	private double currentXPos;
	private double currentYPos;
	private boolean penDown;
	private Color penColor;
	private ImageView turtleImage;

	public Turtle(double d, double e, Image image) {
		this.CANVAS_HEIGHT = e;
		this.CANVAS_WIDTH = d;
		penColor = Color.BLACK;
		penDown = true;
		direction = 0;
		setTurtleImage(image);
		currentXPos = d/2 - this.getTurtleImage().getFitWidth()/2;
		currentYPos = e/2 - this.getTurtleImage().getFitHeight()/2;
	}

	
	public double getPastXPos() {
		return pastXPos;
	}

	public void setPastXPos(double pastXPos) {
		this.pastXPos = pastXPos;
	}

	public double getPastYPos() {
		return pastYPos;
	}

	public void setPastYPos(double pastYPos) {
		this.pastYPos = pastYPos;
	}

	public double getCurrentXPos() {
		return currentXPos;
	}

	public void setCurrentXPos(double currentXPos) {

		this.currentXPos = currentXPos;
		turtleImage.setX(currentXPos);

	}

	public double getCurrentYPos() {
		return currentYPos;
	}

	public void setCurrentYPos(double currentYPos) {
		this.currentYPos = currentYPos;
		turtleImage.setY(currentYPos);
	}

	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown(boolean penDown) {
		this.penDown = penDown;
	}

	public ImageView getTurtleImage() {
		return turtleImage;
	}

	public void setTurtleImage(Image image) {
		ImageView view = new ImageView(image);
		this.turtleImage = view;

		if (image.getHeight() > 100 || image.getWidth() > 100) {
			this.turtleImage.setFitHeight(100);
			this.turtleImage.setFitWidth(100);
		} else {
			this.turtleImage.setFitHeight(image.getHeight());
			this.turtleImage.setFitWidth(image.getWidth());
		}

		this.turtleImage.setX(currentXPos);
		this.turtleImage.setY(currentYPos);
		// this.turtleImage.setRotate(90);
	}

	public int getTurtleID() {
		return turtleID;
	}

	public void setTurtleID(int turtleID) {
		this.turtleID = turtleID;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}

	public void setVisible(boolean showing) {
		turtleImage.setVisible(showing);
	}

	public boolean getVisible() {
		return turtleImage.isVisible();
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		// double oldDirection = getDirection();
		// double rotation = oldDirection - direction;
		this.direction = direction;

		this.turtleImage.setRotate(-direction);
	}

}

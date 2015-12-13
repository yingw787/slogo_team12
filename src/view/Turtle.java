package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Tooltip;
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
	private int index;
	private boolean penDown;
	private Color penColor;
	private ImageView turtleImage;
	private List<String> statsList;
	private Tooltip statsTooltip;
	private boolean isFenced;

	public Turtle(double d, double e, Image image, int ID) {
		this.CANVAS_HEIGHT = 405;
		this.CANVAS_WIDTH = 575;
		this.penColor = Color.BLACK;
		this.penDown = true;
		this.direction = 0;
		this.turtleID = ID;
		this.isFenced = false;
	
		statsList = new ArrayList<String>();
		
		
		
		try{
		setTurtleImage(image);
		currentXPos = d/2 - this.getTurtleImage().getFitWidth()/2;
		currentYPos = e/2 - this.getTurtleImage().getFitHeight()/2;
		this.getTurtleImage().setX(currentXPos);
		this.getTurtleImage().setY(currentYPos);
		}catch (Exception fail){
			//image was bad
			
			System.out.println("bad image");
		}
		
		updateTooltip();
	}
	
	public void setFenced(boolean fenced) {
		isFenced = fenced;
	}


	private void updateTooltip() {
		initStatsList();
		statsTooltip.setText(statsList.toString());
		Tooltip.install(this.getTurtleImage(), statsTooltip );
	}

	
	private void initStatsList() {
		statsList.clear();
		double xdisplay =currentXPos-278.5;
		double ydisplay = currentYPos-205;
		statsList.add("Turtle ID: "+turtleID+"\n");
		statsList.add("Pen down: "+penDown+"\n");
		statsList.add("Coordinates: "+ xdisplay+", " + ydisplay+"\n");
		statsList.add("Heading: "+direction+"\n");
		statsList.add("Pen Color" +penColor.toString()+"\n");
		
		
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
		if (currentXPos > CANVAS_WIDTH && isFenced) {
			currentXPos = CANVAS_WIDTH;
		} else if (currentXPos < 0 && isFenced) {
			currentXPos = 0;
		}
		this.currentXPos = currentXPos;
		turtleImage.setX(currentXPos);
		updateTooltip();
	}

	public double getCurrentYPos() {
		return currentYPos;
	}

	public void setCurrentYPos(double currentYPos) {
		if (currentYPos > CANVAS_HEIGHT && isFenced) {
			currentYPos = CANVAS_HEIGHT;
		} else if (currentYPos < 0 && isFenced) {
			currentYPos = 0;
		}
		this.currentYPos = currentYPos;
		turtleImage.setY(currentYPos);
		updateTooltip();
	}

	public boolean isPenDown() {
		return penDown;
	}

	public void setPenDown(boolean penDown) {
		this.penDown = penDown;
		updateTooltip();
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
		
		makeTooltip();
	
	}


	private void makeTooltip() {
		statsTooltip = new Tooltip(" ");
		Tooltip.install(getTurtleImage(), statsTooltip);
	}

	public int getTurtleID() {
		return turtleID;
	}

	public void setTurtleID(int turtleID) {
		this.turtleID = turtleID;
		updateTooltip();
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
		updateTooltip();
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
		
		updateTooltip();
	}


    public void setPenIndex (int index) {
       this.index = index; 
    }
    public int getPenIndex(){
        return index;
    }

}

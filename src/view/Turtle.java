package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class Turtle {
    private int turtleID;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private double pastXPos;
    private double pastYPos;
    private double currentXPos;
    private double currentYPos;
    private boolean penDown;
    private Color penColor;
    private ImageView turtleImage;

    public Turtle (int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        penColor = Color.BLACK;
        penDown = true;
    }

    public double getPastXPos () {
        return pastXPos;
    }

    public void setPastXPos (double pastXPos) {
        this.pastXPos = pastXPos;
    }

    public double getPastYPos () {
        return pastYPos;
    }

    public void setPastYPos (double pastYPos) {
        this.pastYPos = pastYPos;
    }

    public double getCurrentXPos () {
        return currentXPos;
    }

    public void setCurrentXPos (double currentXPos) {
        setPastXPos(this.currentXPos);
        this.currentXPos = currentXPos;
        turtleImage.setX(currentXPos);

    }

    public double getCurrentYPos () {
        return currentYPos;
    }

    public void setCurrentYPos (double currentYPos) {
        setPastYPos(this.currentYPos);
        this.currentYPos = currentYPos;
        turtleImage.setY(currentYPos);
    }

    public boolean isPenDown () {
        return penDown;
    }

    public void setPenDown (boolean penDown) {
        this.penDown = penDown;
    }

    public ImageView getTurtleImage () {
        return turtleImage;
    }

    public void setTurtleImage (Image turtleImage) {
        ImageView view = new ImageView(turtleImage);
        this.turtleImage = view;
        this.turtleImage.setX(currentXPos);
        this.turtleImage.setY(currentYPos);
    }

    public int getTurtleID () {
        return turtleID;
    }

    public void setTurtleID (int turtleID) {
        this.turtleID = turtleID;
    }

    public Color getPenColor () {
        return penColor;
    }

    public void setPenColor (Color penColor) {
        this.penColor = penColor;
    }
    
    public void setVisible(boolean showing){
    	turtleImage.setVisible(showing);
    }
    public boolean getVisible(){
    	return turtleImage.isVisible();
    }

}

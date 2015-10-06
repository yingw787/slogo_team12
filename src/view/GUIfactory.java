package view;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GUIfactory {

	
	private ResourceBundle myResources;
	
	public GUIfactory(ResourceBundle bundle){
		
			myResources = bundle;
	}
	
	public BorderPane makeBorderPane(){
		
		return new BorderPane();
		
		}
	
	public Button makeButton(String name){
		//if tree for all the different types of buttons
		
		return null;
	}
	public VBox makeVBox(int width, int height){
		
		
		return null;
		
	}
	public void makeTextField(){
		
		
		//return null;
	}
	
}

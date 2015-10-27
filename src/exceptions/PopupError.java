package exceptions;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupError {

	public void generateError(String errorMessage){
		
		Stage errorStage = new Stage(); 
		Text text = new Text(errorMessage);
		StackPane root = new StackPane(); 
		root.setPadding(new Insets(20, 20, 20, 20));
		root.getChildren().add(text);
		Scene scene = new Scene(root);
		errorStage.setScene(scene);
		errorStage.centerOnScreen();
		errorStage.show(); 
		
		
	}
	
	
}

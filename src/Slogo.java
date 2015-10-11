import javafx.application.Application;
import javafx.stage.Stage;
import model.ParseModel;

import java.util.HashMap;
import java.util.Map;

import commands.Command;
import commands.Constant;
import commands.Forward;


public class Slogo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		String input = "#noianofdivnaoidv\nforward :myVar forward ( forward (20 30) forward 40) forward 33\n";
		ParseModel parser = new ParseModel(input, "resources/languages/English");
		parser.createParseModel();
		parser.printParseModel();
	}
	
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

}

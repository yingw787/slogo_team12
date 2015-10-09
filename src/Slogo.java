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
		String input = "forward forward 50 forward 20";
		Map<String,Command> funcMap = new HashMap<String,Command>();
		funcMap.put("Forward", new Forward());
		funcMap.put("Constant", new Constant());
		ParseModel parser = new ParseModel(input, funcMap, "resources/languages/English");
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

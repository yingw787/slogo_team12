import javafx.application.Application;
import javafx.stage.Stage;
import model.ParseModel;

import java.util.HashMap;
import java.util.Map;

import commands.Function;


public class Slogo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		String input = "forward 50 23 forward 20 20";
		Map<String,Function> funcMap = new HashMap<String,Function>();
		funcMap.put("forward", new Forward());
		ParseModel parser = new ParseModel(input, funcMap);
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

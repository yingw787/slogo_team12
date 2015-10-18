import java.util.List;

import engine.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ExpressionNode;
import model.ParseModel;
import model.Translator;


public class Slogo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		String input = "# go forward 70 [the hard way]\nfd sm sum sum sum 10 20 30 5 5";
		ParseModel parser = new ParseModel(input, "resources/languages/English");
		List<ExpressionNode> parseModel = parser.createParseModel();
//		parser.printParseModel();
		Controller c = new Controller();
		Translator t = new Translator(parseModel, c);
		t.executeCommands();
	}
	
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

}

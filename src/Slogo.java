import javafx.application.Application;
import javafx.stage.Stage;
import model.ExpressionNode;
import model.ParseModel;
import model.Translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commands.Command;
import commands.Constant;
import commands.Forward;
import engine.Controller;


public class Slogo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		String input = "#noianofdivnaoidv\nforward forward [forward 40 set :var 20] forward 33\n";
		ParseModel parser = new ParseModel(input, "resources/languages/English");
		List<ExpressionNode> parseModel = parser.createParseModel();
		//parser.printParseModel();
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

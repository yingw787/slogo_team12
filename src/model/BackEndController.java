package model;

import java.util.List;
import java.util.Queue;

import engine.Controller;

public class BackEndController {
	private ParseModel myParser;
	private Controller myController;
	
	public BackEndController(String input, String language) {
		myController = new Controller();
		myParser = new ParseModel(input, "resources/languages/" + language);
	}
	
	public Queue<TurtleStatus> generateTurtleCommands() {
		List<ExpressionNode> parseModel = myParser.createParseModel();
		Translator translator = new Translator(parseModel, myController);
		return translator.executeCommands();
	}

}

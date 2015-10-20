package model;

import java.util.List;
import java.util.Queue;

import engine.Controller;

public class BackEndController {
	private ParseModel myParser;
	private Controller myController;
	
	public BackEndController(Controller controller) {
		myController = controller;
	
	}
	
	public Queue<TurtleStatus> generateTurtleCommands(String input, String language) {
		
		
		myParser = new ParseModel(input, "resources/languages/" + language);
		List<ExpressionNode> parseModel = myParser.createParseModel();
		
		myParser.printParseModel();
		Translator translator = new Translator(parseModel, myController);
		return translator.executeCommands();
	}

}

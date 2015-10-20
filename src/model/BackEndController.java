package model;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import commands.UserCommand;
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
		Map<String,UserCommand> userCommands = myParser.getUserCommands();
		myParser.printParseModel();
		Translator translator = new Translator(parseModel, userCommands, myController);
		return translator.executeCommands();
	}

}

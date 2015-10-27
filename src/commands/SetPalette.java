package commands;

import javafx.scene.paint.Color;

public class SetPalette extends DisplayCommand {

	@Override
	public int getNumParameters() {
		return 4;
	}

	@Override
	protected double returnDoubleValue() {
		return getParameterDoubleValue(0);
	}

	@Override
	public void execute() {
		double red = getParameterDoubleValue(1);
		double green = getParameterDoubleValue(2);
		double blue = getParameterDoubleValue(3);
		Color newColor = Color.rgb((int)red, (int)green, (int)blue, 1.0);
		super.getController().setPalette((int)returnDoubleValue(), newColor);
	}

}

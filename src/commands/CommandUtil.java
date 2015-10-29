// This entire file is part of my masterpiece.
// Elizabeth Dowd
package commands;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CommandUtil {
	
	protected double performBinaryDoubleOp(List<Command> params, BiFunction<Double, Double, Double> func) {
		return func.apply(params.get(0).returnDoubleValue(), params.get(1).returnDoubleValue());
	}
	
	protected double performUnaryDoubleOp(List<Command> params, Function<Double, Double> func) {
		return func.apply(params.get(0).returnDoubleValue());
	}
	
	protected double performUnaryTrigOp(List<Command> params, Function<Double, Double> trigFunc) {
		return Math.toDegrees(trigFunc.apply(Math.toRadians(params.get(0).returnDoubleValue())));
	}
	
	protected boolean performBinaryBooleanOp(List<Command> params, BiFunction<Double, Double, Boolean> func) {
		return func.apply(params.get(0).returnDoubleValue(), params.get(1).returnDoubleValue());
	}

}

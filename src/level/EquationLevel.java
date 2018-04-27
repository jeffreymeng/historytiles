/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 26, 2018
 * Purpose: to manage the components of an equation level
 */

package level;

import util.Utils;

public class EquationLevel extends Level {

	// Format modifiers
	// - examples shown are when format modifier is used by itself unless otherwise marked
	// - modifiers can be combined
	// The variable x is not necessarily a variable; variables are placed in random locations.
	public final static String COEFFICIENT = "coefficient"; // ax = b; ax + b = c in combination with CONSTANT_LEFT
	public final static String MULTIPLY = "multiply"; // ax = b; a * (x + b) = c in combination with CONSTANT_LEFT
	public final static String CONSTANT = "constant"; // x + a = b

	private Object[] equation; // an array containing all components of the equation

	// creates an equation with random numbers
	public EquationLevel(int numVariables, String... formatModifiers) {
		this(numVariables, new int[] {
				Utils.randInt(1, 10),
				Utils.randInt(1, 10),
				Utils.randInt(1, 10),
				Utils.randInt(1, 10),
				Utils.randInt(1, 10)
		}, formatModifiers);
	}

	// creates an equation and uses the ints in numbers as numbers in equation, starting at index 0
	// it is possible that not all ints in numbers will be used
	public EquationLevel(int numVariables, int[] numbers, String... formatModifiers) {
		this.numVariables = numVariables;

		String options = "";
		for (int i = 0; i < formatModifiers.length; i++)
			options += formatModifiers[i];
		// merges the format modifiers into a single string
		// e.g. COEFFICIENT, CONSTANT becomes "coefficientconstant"

		boolean coefficient = false, multiplyLeft = false, constantLeft = false; // booleans show whether each option was selected

		if (options.indexOf("coefficient") > -1) // if options included COEFFICIENT
			coefficient = true;
		if (options.indexOf("multiply") > -1) // if options included MULTIPLY
			multiplyLeft = true;
		if (options.indexOf("constant") > -1) // if options included CONSTANT
			constantLeft = true;

		int result = 0; // right side of equation
		int resultIndex = 0; // index of result in equation

		// One option selected

		if (coefficient && !multiplyLeft && !constantLeft
				|| !coefficient && multiplyLeft && !constantLeft) {
			// a * b = c 
			equation = new Object[5];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			addNumber(numbers[1], 2);
			equation[3] = new Operator(Operator.EQUALS);
			result = getValue(0) * getValue(2);
			resultIndex = 4;
		} else if (!coefficient && !multiplyLeft && constantLeft) {
			// a + b = c
			equation = new Object[5];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.ADDITION);
			addNumber(numbers[1], 2);
			equation[3] = new Operator(Operator.EQUALS);
			result = getValue(0) + getValue(2);
			resultIndex = 4;
		}

		// Two options selected

		else if (coefficient && multiplyLeft && !constantLeft) {
			// a * (b * c) = d
			equation = new Object[9];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			addNumber(numbers[1], 3);
			equation[4] = new Operator(Operator.MULTIPLICATION);
			addNumber(numbers[2], 5);
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			result = getValue(0) * getValue(3) * getValue(5);
			resultIndex = 8;
		} else if (coefficient && !multiplyLeft && constantLeft) {
			// a * b + c = d
			equation = new Object[7];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			addNumber(numbers[1], 2);
			equation[3] = new Operator(Operator.ADDITION);
			addNumber(numbers[2], 4);
			equation[5] = new Operator(Operator.EQUALS);
			result = getValue(0) * getValue(2) + getValue(4);
			resultIndex = 6;
		} else if (!coefficient && multiplyLeft && constantLeft) {
			// a * (b + c) = d
			equation = new Object[9];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			addNumber(numbers[1], 3);
			equation[4] = new Operator(Operator.ADDITION);
			addNumber(numbers[2], 5);
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			result = getValue(0) * (getValue(3) + getValue(5));
			resultIndex = 8;
		}

		// Three options selected

		else if (coefficient && multiplyLeft && constantLeft) {
			// a * (b * c + d) = e
			equation = new Object[11];
			addNumber(numbers[0], 0);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			addNumber(numbers[1], 3);
			equation[4] = new Operator(Operator.MULTIPLICATION);
			addNumber(numbers[2], 5);
			equation[6] = new Operator(Operator.ADDITION);
			addNumber(numbers[3], 7);
			equation[8] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[9] = new Operator(Operator.EQUALS);
			result = getValue(0) * ( getValue(3) * getValue(5) + getValue(7) );
			resultIndex = 10;
		}

		addNumber(result, resultIndex);

		addVariables();		
	}

	// hides random numbers in the equation to make them variables
	private void addVariables() {
		for (int i = 0; i < numVariables; i++) {
			int randIndex = Utils.randInt(0, equation.length);
			if (equation[randIndex] instanceof Digit) {
				((Digit) equation[randIndex]).setVisible(false);
			} else if (equation[randIndex] instanceof Number) {
				((Number) equation[randIndex]).setVisible(false);
			} else {
				i--; // do not increment counter if randIndex did not select a Digit or Number (e.g. if an Operator was selected)
				// for loop increments i automatically so decrementing i results in a net change of zero
			}
		}
	}

	// adds a number of the appropriate type (Digit or Number) at a specified location in equation
	private void addNumber(int value, int index) {
		if (value > 9) // if result has more than one digit
			equation[index] = new Number(value);
		else
			equation[index] = new Digit(value);
	}
	
	// attempts to fill a variable with a number
	// returns whether the number was correct (true if it was correct, false otherwise)
	public boolean fill(int index, int number) {
		if (equation[index] instanceof Digit && ((Digit) equation[index]).getValue() == number) {
			((Digit) equation[index]).setVisible(true);
			return true;
		} else if (equation[index] instanceof Number && ((Number) equation[index]).getValue() == number) {
			((Number) equation[index]).setVisible(true);
			return true;
		} else
			return false;
	}

	// gets the value of an equation component at an index
	// returns the value if the component is a Digit or Number
	// returns -1 if the component is not a Digit or Number
	public int getValue(int index) {
		if (equation[index] instanceof Digit)
			return ((Digit) equation[index]).getValue();
		else if (equation[index] instanceof Number)
			return ((Number) equation[index]).getValue();
		else
			return -1;
	}

	// gets the equation component at an index
	public Object getEquationComponent(int index) {
		return equation[index];
	}

	// gets the correct answers for each variable, in order from left to right
	public int[] getAnswers() {
		int[] answers = new int[numVariables];

		for (int i = 0; i < equation.length; i++) { // for each component of the equation

			// if component is a hidden Digit variable
			if (equation[i] instanceof Digit && !((Digit) equation[i]).isVisible()) {
				// fill first empty slot in answers
				for (int j = 0; j < answers.length; j++)			   // for each number in answers
					if (answers[j] == 0) {							   // if slot is unfilled (default value is 0)
						answers[j] = ((Digit) equation[i]).getValue(); // fill slot
						break; 										   // exit for loop so only one slot is filled
					} // if slot is already filled loop continues and looks at next slot
			}

			// if component is a hidden Number variable
			else if (equation[i] instanceof Number && !((Number) equation[i]).isVisible()) {
				// fill first empty slot in answers
				// same logic as above
				for (int j = 0; j < answers.length; j++)
					if (answers[j] == -1) {
						answers[j] = ((Number) equation[i]).getValue();
						break;
					}
			}

		}

		return answers;
	}

	public Object[] getEquation() {
		return equation;
	}

	// converts the equation to a String
	public String toString() {
		String result = "";
		for (int i = 0; i < equation.length; i++) {
			result += equation[i].toString();
		}
		return result;
	}

	public static void main(String[] args) {
		EquationLevel level = new EquationLevel(2, MULTIPLY, CONSTANT, COEFFICIENT);
		System.out.println(level);
	}

}

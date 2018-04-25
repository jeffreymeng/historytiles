/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 26, 2018
 * Purpose:
 */

package level;

import util.Utils;

public class EquationLevel extends Level {

	// format modifiers
	// - examples shown are when format modifier is used by itself unless otherwise marked
	// - modifiers can be combined
	// note that the variable x may be known and some other numbers may be unknown
	final static String COEFFICIENT = "coefficient"; // ax = b; ax + b = c in combination with CONSTANT_LEFT
	final static String MULTIPLY_LEFT = "multiply-left"; // ax = b; a * (x + b) = c in combination with CONSTANT_LEFT
	final static String CONSTANT_LEFT = "constant-left"; // x + a = b
	//final static String DIVIDE_VARIABLE = "divide-variable"; // x/a = b
	//final static String DIVIDE_LEFT = "divide-left"; // (x + a)/b = c; shown in combination with CONSTANT_LEFT

	private int numVariables;
	private Object[] equation;
	private int[][] hiddenDigits; // effectively an array of 3-tuples in format {index, answer} (index in equation)

	public EquationLevel(int numVariables, String... formatModifiers) {
		this(numVariables, new int[] {Utils.randInt(0, 10), Utils.randInt(0, 10), Utils.randInt(0, 10), Utils.randInt(0, 10), Utils.randInt(0, 10)}, formatModifiers);
	}

	public EquationLevel(int numVariables, int[] numbers, String... formatModifiers) {
		this.numVariables = numVariables;

		String options = "";
		for (int i = 0; i < formatModifiers.length; i++)
			options += formatModifiers[i];

		boolean coefficient = false, multiplyLeft = false, constantLeft = false; // booleans show whether each option was selected

		if (options.indexOf("coefficient") > -1)
			coefficient = true;
		if (options.indexOf("multiply-left") > -1)
			multiplyLeft = true;
		if (options.indexOf("constant-left") > -1)
			constantLeft = true;

		int result = 0; // right side of equation
		int resultIndex = 0; // index of result in equation

		// One option selected

		if (coefficient && !multiplyLeft && !constantLeft || !coefficient && multiplyLeft && !constantLeft) {

			// a * b = c 

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Digit(numbers[1]);
			equation[3] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * getDigitValue(2);


		} else if (!coefficient && !multiplyLeft && constantLeft) {

			// a + b = c

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.ADDITION);
			equation[2] = new Digit(numbers[1]);
			equation[3] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) + getDigitValue(2);
			//equation[4] = new Digit(((Digit)equation[0]).getValue() + ((Digit)equation[2]).getValue());

		}

		// Two options selected

		else if (coefficient && multiplyLeft && !constantLeft) {

			// a * (b * c) = d

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(numbers[1]);
			equation[4] = new Operator(Operator.MULTIPLICATION);
			equation[5] = new Digit(numbers[2]);
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * getDigitValue(3) * getDigitValue(5);
			resultIndex = 8;
			//equation[8] = new Digit( ((Digit)equation[0]).getValue() * ((Digit)equation[3]).getValue() * ((Digit)equation[5]).getValue() );

		} else if (coefficient && !multiplyLeft && constantLeft) {

			// a * b + c = d

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Digit(numbers[1]);
			equation[3] = new Operator(Operator.ADDITION);
			equation[4] = new Digit(numbers[2]);
			equation[5] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * getDigitValue(2) * getDigitValue(4);
			resultIndex = 6;

		} else if (!coefficient && multiplyLeft && constantLeft) {

			// a * (b + c) = d

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(numbers[1]);
			equation[4] = new Operator(Operator.ADDITION);
			equation[5] = new Digit(numbers[2]);
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * (getDigitValue(3) + getDigitValue(5));
			resultIndex = 8;

		}

		// Three options selected

		else if (coefficient && multiplyLeft && constantLeft) {

			// a * (b * c + d) = e

			equation[0] = new Digit(numbers[0]);
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(numbers[1]);
			equation[4] = new Operator(Operator.MULTIPLICATION);
			equation[5] = new Digit(numbers[2]);
			equation[6] = new Operator(Operator.ADDITION);
			equation[7] = new Digit(numbers[3]);
			equation[8] = new Operator(Operator.OPEN_PARENTHESES);
			equation[9] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * ( getDigitValue(3) * getDigitValue(5) + getDigitValue(7) );
			resultIndex = 10;

		}

		if (Utils.getDigits(result) > 1)
			equation[resultIndex] = new Number(result);
		else
			equation[resultIndex] = new Digit(result);

		hiddenDigits = new int[numVariables][2];

		addVariables();		
	}

	private void addVariables() {
		for (int i = 0; i < numVariables; i++) {
			int randIndex = Utils.randInt(0, equation.length - 1);
			if (equation[randIndex] instanceof Digit) {
				((Digit)equation[randIndex]).setVisible(false);
				hiddenDigits[i][0] = randIndex;
				hiddenDigits[i][1] = ((Digit)equation[randIndex]).getValue();
			} else if (equation[randIndex] instanceof Number) {
				((Number)equation[randIndex]).setVisible(false);
				hiddenDigits[i][0] = randIndex;
				hiddenDigits[i][1] = ((Number)equation[randIndex]).getValue();
			} else
				i--; // do not increment counter if randIndex did not select a Digit or Number (e.g. if an Operator was selected)
			// for loop increments i automatically so decrementing i results in a net change of zero
		}
	}

	public boolean fill(int index, int number) {
		if (hiddenDigits[index][1] == number) {
			if (equation[index] instanceof Digit) {
				((Digit) equation[index]).setVisible(true);
			} else if (equation[index] instanceof Number)
				((Number) equation[index]).setVisible(true);
			return true;
		} else
			return false;
	}

	public int getDigitValue(int index) {
		if (equation[index] instanceof Digit)
			return ((Digit)equation[index]).getValue();
		else
			return -1;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < equation.length; i++)
			result += equation[i].toString();
		return result;
	}

}

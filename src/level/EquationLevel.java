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

	public EquationLevel(String... formatModifiers) {
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

		int result; // right side of equation
		int resultIndex; // index of result in equation
		
		// One option selected
		
		if (coefficient && !multiplyLeft && !constantLeft || !coefficient && multiplyLeft && !constantLeft) {

			// a * b = c 

			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Digit(Utils.randInt(0, 10));
			equation[3] = new Operator(Operator.EQUALS);
			result = getDigitValue(0) * getDigitValue(2);
			if (Utils.getDigits(result) > 1)
				equation[4] = new Number(result);
			else
				equation[4] = new Digit(result);

		} else if (!coefficient && !multiplyLeft && constantLeft) {

			// a + b = c
			
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.ADDITION);
			equation[2] = new Digit(Utils.randInt(0, 10));
			equation[3] = new Operator(Operator.EQUALS);
			equation[4] = new Digit(((Digit)equation[0]).getValue() + ((Digit)equation[2]).getValue());

		}
		
		// Two options selected
		
		else if (coefficient && multiplyLeft && !constantLeft) {

			// a * (b * c) = d

			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(Utils.randInt(0, 10));
			equation[4] = new Operator(Operator.MULTIPLICATION);
			equation[5] = new Digit(Utils.randInt(0, 10));
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			equation[8] = new Digit( ((Digit)equation[0]).getValue() * ((Digit)equation[3]).getValue() * ((Digit)equation[5]).getValue() );

		} else if (coefficient && !multiplyLeft && constantLeft) {
			
			// a * b + c = d
			
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Digit(Utils.randInt(0, 10));
			equation[3] = new Operator(Operator.ADDITION);
			equation[4] = new Digit(Utils.randInt(0, 10));
			equation[5] = new Operator(Operator.EQUALS);
			equation[6] = new Digit( getDigitValue(0) * getDigitValue(2) * getDigitValue(4) );
					//((Digit)equation[0]).getValue() * ((Digit)equation[2]).getValue() + ((Digit)equation[4]).getValue() );
			
		} else if (!coefficient && multiplyLeft && constantLeft) {
			
			// a * (b + c) = d
			
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(Utils.randInt(0, 10));
			equation[4] = new Operator(Operator.ADDITION);
			equation[5] = new Digit(Utils.randInt(0, 10));
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Operator(Operator.EQUALS);
			equation[8] = new Digit( getDigitValue(0) * (getDigitValue(3) + getDigitValue(5)) );
			
		}
		
		// Three options selected
		
		else if (coefficient && multiplyLeft && constantLeft) {

			// a * (b * c + d) = e

			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(Utils.randInt(0, 10));
			equation[4] = new Operator(Operator.MULTIPLICATION);
			equation[5] = new Digit(Utils.randInt(0, 10));
			equation[6] = new Operator(Operator.ADDITION);
			equation[7] = new Digit(Utils.randInt(0, 10));
			equation[8] = new Operator(Operator.OPEN_PARENTHESES);
			equation[9] = new Operator(Operator.EQUALS);
			equation[10] = new Digit( getDigitValue(0) * ( getDigitValue(3) * getDigitValue(5) + getDigitValue(7) ) );
					//((Digit)equation[0]).getValue() *
					//( ((Digit)equation[3]).getValue() * ((Digit)equation[5]).getValue() + ((Digit)equation[7]).getValue()) );

		}
		
		addVariables();

	}

	/*
	 * Format:
	 *  n	one-digit number
	 *  nn	two-digit number
	 *  nnn	three-digit number
	 *  +	addition
	 *  -	subtraction
	 *  *	multiplication
	 *  /	division
	 *  =	equality
	 *  
	 * Operators are not implied.
	 * Spaces are ignored.
	 */
	/*public EquationLevel(String format) {

		format.trim(); // remove spaces at beginning and end ("  sample string    " becomes "sample string")

		for (int i = 0; i < format.length(); i++) // for each character in format
			if (format.charAt(i) == ' ') // if the character is a space
				format = format.substring(0, i) + format.substring(i + 1); // delete the character
		// "sample string" becomes "samplestring"

		for (int i = 0; i < format.length(); i++) { // for each character in format
			switch (format.charAt(i)) {
			case 'n':
				int numDigits = 1;
				boolean numberContinues = true; // true if next character is possibly another digit of the current number
				while (numberContinues) {
					if (format.charAt(i + numDigits) == 'n') { // if next character to be checked is another digit of the current number
						numDigits++;
						i++; // skip characters already checked in next loop
						numberContinues = true; // character after  character just checked may be another digit
					} else
						numberContinues = false; // if next character is not 'n', then it is not part of this number
				}
				equation[i] = new Number(numDigits);
				break;
			case '+':
				equation[i] = new Operator(Operator.ADDITION);
				break;
			case '-':
				equation[i] = new Operator(Operator.SUBTRACTION);
				break;
			case '*':
				equation[i] = new Operator(Operator.MULTIPLICATION);
				break;
			case '/':
				equation[i] = new Operator(Operator.DIVISION);
				break;
			case '=':
				equation[i] = new Operator(Operator.EQUALS);
				break;
			}
		}
		
		// fill numbers here
		
		addVariables();

	} */
	
	private void addVariables() {
		for (int i = 0; i < numVariables; i++) {
			int randIndex = Utils.randInt(0, equation.length - 1);
			if (equation[randIndex] instanceof Digit)
				((Digit)equation[randIndex]).setVisible(false);
			else if (equation[randIndex] instanceof Number)
				((Number)equation[randIndex]).setVisible(false);
			else
				i--; // do not increment counter if randIndex did not select a Digit or Number
		}
	}
	
	public int getDigitValue(int index) {
		if (equation[index] instanceof Digit)
			return ((Digit)equation[index]).getValue();
		else
			return -1;
	}

}

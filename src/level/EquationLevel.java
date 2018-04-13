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
	final static String MULTIPLY_LEFT = "multiply-left"; // a * (x + b) = c; shown in combination with CONSTANT_LEFT
	final static String CONSTANT_LEFT = "constant-left"; // x + a = b
	//final static String DIVIDE_VARIABLE = "divide-variable"; // x/a = b
	//final static String DIVIDE_LEFT = "divide-left"; // (x + a)/b = c; shown in combination with CONSTANT_LEFT
	
	private int numVariables;

	private Object[] equation;

	public EquationLevel(String... formatModifiers) {
		String options = "";
		for (int i = 0; i < formatModifiers.length; i++)
			options += formatModifiers[i];

		boolean coefficient = false, multiplyLeft = false, constantLeft = false;

		if (options.indexOf("coefficient") > -1)
			coefficient = true;
		if (options.indexOf("multiply-left") > -1)
			multiplyLeft = true;
		if (options.indexOf("constant-left") > -1)
			constantLeft = true;

		// if options included COEFFICIENT or MULTIPLY_LEFT only (equivalent)
		if (coefficient && !multiplyLeft && !constantLeft || !coefficient && multiplyLeft && !constantLeft) {
			
			//
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Digit(Utils.randInt(0, 10));
			equation[3] = new Operator(Operator.EQUALS);
			equation[4] = new Digit(((Digit)equation[0]).getValue() * ((Digit)equation[2]).getValue());

		} else if (coefficient && multiplyLeft && !constantLeft) {
			
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.MULTIPLICATION);
			equation[2] = new Operator(Operator.OPEN_PARENTHESES);
			equation[3] = new Digit(Utils.randInt(0, 10));
			equation[4] = new Operator(Operator.MULTIPLICATION);
			equation[5] = new Digit(Utils.randInt(0, 10));
			equation[6] = new Operator(Operator.CLOSE_PARENTHESES);
			equation[7] = new Digit(((Digit)equation[0]).getValue() * ((Digit)equation[3]).getValue() * ((Digit)equation[5]).getValue());
			
		} else if (coefficient && multiplyLeft && constantLeft) {
			
			// n * (n * n + n) = n
			
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
			equation[10] = new Digit( ((Digit)equation[0]).getValue() *
					( ((Digit)equation[3]).getValue() * ((Digit)equation[5]).getValue() + ((Digit)equation[7]).getValue()) );
			
		} else if (!coefficient && !multiplyLeft && constantLeft) {
			
			equation[0] = new Digit(Utils.randInt(0, 10));
			equation[1] = new Operator(Operator.ADDITION);
			equation[2] = new Digit(Utils.randInt(0, 10));
			equation[3] = new Operator(Operator.EQUALS);
			equation[4] = new Digit(((Digit)equation[0]).getValue() + ((Digit)equation[2]).getValue());
			
		}

	}


	/*
	 * Format:
	 *  n	one-digit number
	 *  nn	two-digit number
	 *  +	addition
	 *  -	subtraction
	 *  *	multiplication
	 *  /	division
	 *  
	 * Operators are not implied.
	 * Spaces are ignored.
	 */
	public EquationLevel(String format) {

		format.trim(); // remove spaces at beginning and end (e.g. "  sample string    " becomes "sample string")

		for (int i = 0; i < format.length(); i++) // for each character in format
			if (format.charAt(i) == ' ') // if the character is a space
				format = format.substring(0, i) + format.substring(i + 1); // delete the character

		for (int i = 0; i < format.length(); i++) {
			switch (format.charAt(i)) {
			case 'n':
				int numDigits = 1;
				boolean numberContinues = true; // true if next character is possibly another digit of the current number
				while (numberContinues) {
					if (format.charAt(i + numDigits) == 'n') { // if next character to be checked is another digit of the current number
						numDigits++;
						numberContinues = true; // character after next character may still be another digit
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
			}
		}

	}

}

/*
package level;

public class EquationLevel extends Level {
	private String format;
	/*
 * Format:
 *  x		one-digit blank
 *  x{y}	y-digit blank
 *  x(z)	one-digit blank with answer equal to z
 *  x{y}(z)	y-digit blank with answer equal to z
 *  n		number
 *  +		addition
 *  -		subtraction
 *  *		multiplication
 *  /		division
 *  
 *  Operators are not implied (e.g. "xn" refers to a single two-digit number with a blank tens digit, not "x * n").
 *  Spaces are ignored.


	private Object[] equation;

	public EquationLevel(String format) {
		this.format = format;
		/*
		for (int i = 0; i < this.format.length(); i++) {
			switch (this.format.charAt(i)) {
			case 'x':
				equation[i] = new Digit
			}
		}

	}
}
 */

/*
 * Programmer: Jeffrey Meng and Dylan Yang
 * Date: Mar 26, 2018
 * Purpose:
 */

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
	 */
	
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
		*/
	}
}

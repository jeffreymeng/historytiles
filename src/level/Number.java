/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 12, 2018
 * Purpose: TODO
 */
package level;

import util.Utils;

public class Number {

	//private int numDigits;
	private int[] digits;

	public Number(int numDigits) {
		//this.numDigits = numDigits;
		digits = new int[numDigits];

		// first digit is random number from 1 (inclusive) to 10 (exclusive)
		digits[0] = Utils.randInt(1, 10);

		// each other digit is random number from 1 (inclusive) to 10 (exclusive)
		for (int i = 1; i < digits.length; i++)
			digits[i] = Utils.randInt(0, 10);
	}

	public Number(int... digits) {
		this.digits = digits;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < digits.length; i++)
			string += digits[i];
		return string;
	}
}

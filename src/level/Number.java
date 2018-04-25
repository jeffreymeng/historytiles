/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 12, 2018
 * Purpose: TODO
 */
package level;

import util.Utils;

public class Number {

	//private int numDigits;
	private Digit[] digits;
	private boolean isVisible;

	public Number(int numDigits) {
		//this.numDigits = numDigits;
		digits = new Digit[numDigits];

		// first digit is random number from 1 (inclusive) to 10 (exclusive)
		digits[0] = new Digit(Utils.randInt(1, 10));

		// each other digit is random number from 0 (inclusive) to 10 (exclusive)
		for (int i = 1; i < digits.length; i++)
			digits[i] = new Digit(Utils.randInt(0, 10));
	}

	public Number(int... digits) {
		for (int i = 0; i < digits.length; i++)
			this.digits[i] = new Digit(digits[i]);
	}

	public Number(Digit... digits) {
		this.digits = digits;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < digits.length; i++)
			string += digits[i];
		return string;
	}

	public Digit[] getDigits() {
		return digits;
	}

	public void setDigits(int... digits) {
		for (int i = 0; i < digits.length; i++)
			this.digits[i] = new Digit(digits[i]);
	}

	public void setDigits(Digit... digits) {
		this.digits = digits;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public int getNumDigits() {
		return digits.length;
	}
	
	public int getValue() {
		return Integer.parseInt(this.toString()); // convert the string interpretation of this Number to an int
	}
}

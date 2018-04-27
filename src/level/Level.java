/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: This class creates the base class to manage a level, from which other more specific level types extend off of.
 */
package level;

public class Level {

	protected int num1;
	protected int num2;
	protected int numVariables;
	
	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public int getNumVariables() {
		return numVariables;
	}
	
	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}
	
}

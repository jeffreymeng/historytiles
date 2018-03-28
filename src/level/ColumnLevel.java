/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: to manage the numbers in a column-format level
 */

package level;

import util.Utils;

public class ColumnLevel extends Level {
	
	private int num1;
	private int num2;
	private int[][] numGrid;
	private char operation;
	
	public final static char ADDITION = '+';
	public final static char SUBTRACTION = '-';
	public final static char MULTIPLICATION = '*';
	public final static char DIVISION = '/';
	
	public ColumnLevel() {
		this.operation = ADDITION;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);
	}
	
	public ColumnLevel(char operation) {
		this.operation = operation;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);
		if (operation == SUBTRACTION && num2 > num1)
			swapNums();
	}
	
	public ColumnLevel(char operation, int num1, int num2) {
		this.operation = operation;
		this.num1 = num1;
		this.num2 = num2;
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();
	}
	
	public ColumnLevel(char operation, int num1min, int num1max, int num2min, int num2max) {
		this.operation = operation;
		num1 = Utils.randInt(num1min, num1max);
		num2 = Utils.randInt(num2min, num2max);
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();
	}
	
	public void swapNums() {
		int temp = num1;
		num1 = num2;
		num2 = temp;
	}

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

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();
	}
	
}

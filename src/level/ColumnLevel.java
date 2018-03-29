/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: to manage the numbers in a column-format level
 */

package level;

import util.Utils;

public class ColumnLevel extends Level {

	private int[][] numGrid = {{}, {}, {}};
	private char operation;

	final static char ADDITION = '+';
	final static char SUBTRACTION = '-';
	final static char MULTIPLICATION = '*';
	final static char DIVISION = '/';

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
		else if (operation == ADDITION && String.valueOf(num2).length() > String.valueOf(num1).length())
			swapNums();

		String num1String = String.valueOf(num1);
		numGrid[0] = new int[num1String.length()];
		for (int i = 0; i < num1String.length(); i++) {
			numGrid[0][i] = Character.getNumericValue(num1String.charAt(i));
		}

		String num2String = String.valueOf(num2);
		numGrid[1] = new int[num2String.length()];
		for (int i = 0; i < num2String.length(); i++)
			numGrid[1][i] = Character.getNumericValue(num2String.charAt(i));
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

	public void printNumGrid() {
		System.out.println(numGrid[0][0]);
		System.out.println(numGrid[0][1]);
		System.out.println(numGrid[0][2]);

		System.out.println(numGrid[1][0]);
		System.out.println(numGrid[1][1]);
		System.out.println(numGrid[1][2]);
	}

	public static void main(String[] args) {
		ColumnLevel cl = new ColumnLevel(ColumnLevel.ADDITION);
		cl.printNumGrid();
	}

}

/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: to manage the numbers in a column-format level
 */

package level;

import util.Utils;

public class ColumnLevel extends Level {

	private Digit[][] numGrid = { {/* first number */}, {/* second number */}, {/* sum/difference/product/quotient */} };
	private int numVariables;
	private int[][] hiddenDigits; // effectively an array of 3-tuples in format {row, column, answer}
	// stores answers and their locations in numGrid
	private char operation; // should be one of the final static variables below

	final static char ADDITION = '+';
	final static char SUBTRACTION = '-';
	final static char MULTIPLICATION = '*';
	final static char DIVISION = '/';

	public ColumnLevel() {
		this.operation = ADDITION;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		// if num2 has more digits than num1
		if (Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation) {
		this.operation = operation;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		// if:	 operation is subtraction and num2 > num1
		//    or operation is addition and num2 has more digits than num1
		// then: switch num1 and num2
		if (operation == SUBTRACTION && num2 > num1 || operation == ADDITION && Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation, int numVariables) {
		this.operation = operation;
		this.numVariables = numVariables;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		// if:	 operation is subtraction and num2 > num1 or
		//       operation is addition and num2 has more digits than num1
		// then: switch num1 and num2
		if (operation == SUBTRACTION && num2 > num1 || operation == ADDITION && Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[this.numVariables][3];
	}

	public ColumnLevel(char operation, int num1, int num2) {
		this.operation = operation;
		this.num1 = num1;
		this.num2 = num2;

		// if:   operation is subtraction and num2 > num1
		//       operation is addition and num2 has more digits than num1
		// then: switch num1 and num2
		if (operation == SUBTRACTION && this.num2 > this.num1
				|| operation == ADDITION
				&& Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation, int num1min, int num1max, int num2min,
			int num2max) {
		this.operation = operation;
		num1 = Utils.randInt(num1min, num1max);
		num2 = Utils.randInt(num2min, num2max);

		// if:   operation is subtraction and num2 > num1
		//       operation is addition and num2 has more digits than num1
		// then: switch num1 and num2
		if (operation == SUBTRACTION && this.num2 > this.num1
				|| operation == ADDITION
				&& Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	/**
	 * Switches num1 and num2.
	 */
	public void swapNums() {
		int temp = num1;
		num1 = num2;
		num2 = temp;
	}

	/**
	 * Fills numGrid with the individual digits of each number.
	 * @param num1 the first number
	 * @param num2 the second number
	 */
	public void fillNumGrid(int num1, int num2) {
		String num1String = String.valueOf(num1); // converts num1 to string
		numGrid[0] = new Digit[num1String.length()];
		for (int i = 0; i < num1String.length(); i++) {
			numGrid[0][i] = new Digit(Character.getNumericValue(num1String
					.charAt(i)), true);
			// splits num1String into individual digits and stores them in numGrid
		}

		String num2String = String.valueOf(num2);
		numGrid[1] = new Digit[num2String.length()];
		for (int i = 0; i < num2String.length(); i++) {
			numGrid[1][i] = new Digit(Character.getNumericValue(num2String
					.charAt(i)), true);
			// splits num2String into individual digits and stores them in numGrid
		}

		String resultString; // the sum/difference/product/quotient
		switch (operation) {
		case ADDITION:
			resultString = String.valueOf(num1 + num2);
			break;
		case SUBTRACTION:
			resultString = String.valueOf(num1 - num2);
			break;
		case MULTIPLICATION:
			resultString = String.valueOf(num1 * num2);
			break;
		case DIVISION:
			resultString = String.valueOf(num1 / num2);
			break;
		default:
			resultString = "";
			break;
		}

		numGrid[2] = new Digit[resultString.length()];
		for (int i = 0; i < resultString.length(); i++) {
			numGrid[2][i] = new Digit( Character.getNumericValue( resultString.charAt(i) ), true );
			// splits resultString into individual digits and stores them in numGrid
		}
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
		if (operation == SUBTRACTION && num2 > num1
				|| operation == ADDITION 
				// if num2 has more digits than num1
				&& Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();
	}

	/**
	 * @return the number of objects in numGrid
	 */
	public int getNumGridLength() {
		int sum = 0;
		// for each row in numGrid
		for (int i = 0; i < numGrid.length; i++)
			sum += numGrid[i].length;
		return sum;
	}

	public void printNumGrid() { // for debugging
		System.out.println("numGrid");
		String text = "";
		for (Digit[] row : numGrid) {

			for (Digit col : row) {

				text += (int) col.getValue() + " ";
			}
			text += "\n";
		}
		System.out.print(text);
		System.out.println("Length: " + getNumGridLength());
	}

	public Digit[][] getNumGrid() {
		return numGrid;
	}

	/**
	 * Adds variables in random locations and stores them in hiddenDigits
	 */
	public void addVariables() {
		int randRowIndex;
		int randColIndex;

		// loops n times where n equals numVariables
		for (int i = 0; i < numVariables; i++) {
			// generate random row and column
			randRowIndex = Utils.randInt(0, numGrid.length);
			randColIndex = Utils.randInt(0, numGrid[1].length);

			// store the random row and column in hiddenDigits
			hiddenDigits[i][0] = randRowIndex;
			hiddenDigits[i][1] = randColIndex;

			//  store the correct value in hiddenDigits; this is the answer
			hiddenDigits[i][2] = numGrid[randRowIndex][randColIndex].getValue();

			// hide the Digit object in numGrid
			numGrid[randRowIndex][randColIndex].setVisible(false);
		}

		// ascending exchange sort, first by row and then by column; ignores answer
		// e.g. [2, 1, 9], [0, 1, 7], [0, 2, 8] sorts into [0, 1, 7], [0, 2, 8], [2, 1, 9]
		int[] temp;
		for (int i = 0; i < hiddenDigits.length - 1; i++) {
			for (int j = i + 1; j < hiddenDigits.length; j++) {
				if (hiddenDigits[i][0] > hiddenDigits[j][0]) {
					temp = hiddenDigits[i];
					hiddenDigits[i] = hiddenDigits[j];
					hiddenDigits[j] = temp;
				} else if (hiddenDigits[i][0] == hiddenDigits[j][0] && hiddenDigits[i][1] > hiddenDigits[j][1]) {
					temp = hiddenDigits[i];
					hiddenDigits[i] = hiddenDigits[j];
					hiddenDigits[j] = temp;
				}
			}
		}
	}

	public boolean fill(int index, int number) {
		if (hiddenDigits[index][2] == number) {
			int numRow = hiddenDigits[index][0];
			int numCol = hiddenDigits[index][1];
			numGrid[numRow][numCol].setVisible(true);
			return true;
		} else
			return false;

	}

	public int[] getAnswers() {
		int[] answers =  new int[hiddenDigits.length];
		for (int i = 0; i < hiddenDigits.length; i++)
			answers[i] = hiddenDigits[i][2];
		return answers;
	}

	public Digit getDigit(int row, int col) {
		return numGrid[row][col];
	}

	public Digit[] getRow(int index) {
		return numGrid[index];
	}

	public Digit[] getCol(int index) {
		Digit[] column = new Digit[numGrid.length];
		for (int i = 0; i < numGrid.length; i++)
			column[i] = numGrid[i][ numGrid[i].length - index ];
		return column;
	}

	public static void main(String[] args) {
		ColumnLevel cl = new ColumnLevel(ColumnLevel.ADDITION);
		cl.printNumGrid();
		cl.addVariables();
		int[] answers = cl.getAnswers();
		Utils.printArray(answers);
		System.out.println(cl.fill(0, answers[0]));
	}

}

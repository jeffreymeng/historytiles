/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: to manage the numbers in a column-format level
 */

package level;

import util.Utils;

public class ColumnLevel extends Level {
	private Digit[][] numGrid = { {/* first number */}, {/* second number */}, {/* sum/difference/product/quotient */} };
	private Digit[][] alignedNumGrid; // same as numGrid except all numbers are right-aligned, with empty spaces set to null
	private int numVariables;
	private int[][] hiddenDigits; // effectively an array of 3-tuples in format {row, column, answer}
	// stores answers and their locations in numGrid
	private char operation; // should be one of the final static variables below
	private int maxNumLength;

	final static char ADDITION = '+';
	final static char SUBTRACTION = '-';
	final static char MULTIPLICATION = '*';
	final static char DIVISION = '/';

	/**
	 * Constructs a new ColumnLevel. The operation is addition by default.
	 */
	public ColumnLevel() {
		this.operation = ADDITION;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		checkNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	/**
	 * Constructs a new ColumnLevel with a specified operation.
	 * @param operation the operation for this level
	 */
	public ColumnLevel(char operation) {
		this.operation = operation;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		checkNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	/**
	 * Constructs a new ColumnLevel with a specified operation and number of variables
	 * @param operation    the operation for this level
	 * @param numVariables the number of variables for this level
	 */
	public ColumnLevel(char operation, int numVariables) {
		this.operation = operation;
		this.numVariables = numVariables;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		checkNums();

		fillNumGrid(num1, num2);

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[this.numVariables][3];
	}

	/**
	 * Constructs a new ColumnLevel with a specified operation, num1, and num2.
	 * @param operation the operation for this level
	 * @param num1      the first addend or factor, the minuend, or the dividend
	 * @param num2      the second addend or factor, the subtrahend, or the divisor
	 */
	public ColumnLevel(char operation, int num1, int num2) {
		this.operation = operation;
		this.num1 = num1;
		this.num2 = num2;

		checkNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	/**
	 * Constructs a new ColumnLevel with a specified operation and range for
	 * generating num1 and num2.
	 * @param operation the operation for this level
	 * @param num1min   the minimum value for num1 (inclusive)
	 * @param num1max   the maximum value for num1 (exclusive)
	 * @param num2min   the minimum value for num2 (inclusive)
	 * @param num2max   the maximum value for num2 (exclusive)
	 */
	public ColumnLevel(char operation, int num1min, int num1max, int num2min,
			int num2max) {
		this.operation = operation;
		num1 = Utils.randInt(num1min, num1max);
		num2 = Utils.randInt(num2min, num2max);

		checkNums();

		fillNumGrid(num1, num2);

		numVariables = 2;

		// number of 3-tuples matches number of variables
		hiddenDigits = new int[numVariables][3];
	}

	/**
	 * Checks if num1 and num2 need to be swapped, and swaps them if necessary.
	 * if:	 operation is subtraction and num2 > num1
	 * or:	 operation is addition and num2 has more digits than num1
	 * then: switch num1 and num2
	 */
	private void checkNums() {
		if (operation == SUBTRACTION && num2 > num1 || operation == ADDITION && Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();
	}
	
	/**
	 * Switches num1 and num2.
	 */
	private void swapNums() {
		int temp = num1;
		num1 = num2;
		num2 = temp;
	}

	/**
	 * Fills numGrid with the individual digits of each number.
	 * @param num1 the first number
	 * @param num2 the second number
	 */
	private void fillNumGrid(int num1, int num2) {
		/*
		 * For each digit of num1:
		 * - find the value of that digit by converting num1 to a string and using
		 *   the charAt and getNumericValue methods
		 * - construct a new Digit using that value with visibility set to true
		 * - store that Digit in the next empty space in the first row of numGrid
		 */
		String num1String = String.valueOf(num1); // converts num1 to string
		numGrid[0] = new Digit[num1String.length()]; // set the first row of the numGrid to the length of num1

		// converts each character in num1String into an integer
		// constructs Digits using that integer value
		// stores the Digits in numGrid
		for (int i = 0; i < num1String.length(); i++)
			numGrid[0][i] = new Digit(Character.getNumericValue(num1String
					.charAt(i)), true);

		/*
		 * For each digit of num2:
		 * - find the value of that digit by converting num2 to a string and using
		 *   the charAt and getNumericValue methods
		 * - construct a new Digit using that value with visibility set to true
		 * - store that Digit in the next empty space in the second row of numGrid
		 * 
		 * Process used is identical to that used for num1.
		 */
		String num2String = String.valueOf(num2);
		numGrid[1] = new Digit[num2String.length()];
		for (int i = 0; i < num2String.length(); i++)
			numGrid[1][i] = new Digit(Character.getNumericValue(num2String
					.charAt(i)), true);

		String resultString; // the sum, difference, product, or quotient as a String
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

		// converts each character in resultString into an integer
		// constructs Digits using that integer value
		// stores the Digits in the third row of numGrid
		numGrid[2] = new Digit[resultString.length()];
		for (int i = 0; i < resultString.length(); i++)
			numGrid[2][i] = new Digit( Character.getNumericValue( resultString.charAt(i) ), true );



		maxNumLength = 0; // the number of digits in the longest number
		// loops through each number; set its length equal to maxNumLength if it is the longest yet checked
		for (int i = 0; i < numGrid.length; i++)
			if (numGrid[i].length > maxNumLength)
				maxNumLength = numGrid[i].length;

		// fills alignedNumGrid
		alignedNumGrid = new Digit[numGrid.length][maxNumLength];
		// loops through each Digit in numGrid
		// maxNumLength - numGrid[i].length is equal to amount of whitespace needed at beginning of line
		for (int i = 0; i < numGrid.length; i++)
			for (int j = 0; j < numGrid[i].length; j++)
				alignedNumGrid[i][j + (maxNumLength - numGrid[i].length)] = numGrid[i][j];
	}

	/**
	 * Returns the operation for this level.
	 * @return
	 */
	public char getOperation() {
		return operation;
	}

	/**
	 * Sets the operation for this level.
	 * @param operation the new operation for this level
	 */
	public void setOperation(char operation) {
		this.operation = operation;
		
		checkNums(); // whether num1 and num2 need to be switched depends on operation
	}

	/**
	 * Returns the numGrid of this level.
	 * @return a Digit matrix containing all objects in numGrid
	 */
	public Digit[][] getNumGrid() {
		return numGrid;
	}

	/**
	 * Returns the alignedNumGrid of this level.
	 * @return a Digit matrix containing all objects in alignedNumGrid
	 */
	public Digit[][] getAlignedNumGrid() {
		return alignedNumGrid;
	}

	/**
	 * Returns the length of numGrid.
	 * @return the number of objects in numGrid
	 */
	public int getNumGridLength() {
		int sum = 0;
		// for each row in numGrid
		for (int i = 0; i < numGrid.length; i++)
			sum += numGrid[i].length;
		return sum;
	}
	
	/**
	 * Returns the number of digits in the longest number, including the sum,
	 * difference, product, or quotient, if applicable.
	 * 
	 * @return the length of the longest number.
	 */
	public int getMaxNumLength() {
		return maxNumLength;
	}
	
	/**
	 * Adds variables in random locations and stores them in hiddenDigits.
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

			//  store the correct value (answer) in hiddenDigits
			hiddenDigits[i][2] = numGrid[randRowIndex][randColIndex].getValue();

			// hide the Digit object in numGrid
			numGrid[randRowIndex][randColIndex].setVisible(false);
		}

		// ascending exchange sort, first by row and then by column; ignores answer
		// effectively treats each tuple as a two-digit number
		// the first digit is the row and the second digit is the column
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

	/**
	 * Attempts to fill an empty slot with a number.
	 * 
	 * @param index  the index of the empty slot to fill
	 * @param number the number to input
	 * 
	 * @return whether the number is correct, as a boolean
	 */
	public boolean fill(int index, int number) {
		// if:   the answer value stored in hiddenDigits at the given index is equal to the input
		// then: make the Digit at the correct location in numGrid visible
		//		 return true since the number was correct
		if (hiddenDigits[index][2] == number) {
			int numRow = hiddenDigits[index][0];
			int numCol = hiddenDigits[index][1];
			numGrid[numRow][numCol].setVisible(true);
			return true;
		} else // if the input is not equal to the correct answer
			return false; // return false since the number was not correct

	}

	/**
	 * Returns the correct answers.
	 * @return an int array of answers
	 */
	public int[] getAnswers() {
		int[] answers =  new int[hiddenDigits.length]; // create empty integer array
		// for each answer stored in hiddenDigits:
		// - set the next empty slot in the answers array equal to that answer
		for (int i = 0; i < hiddenDigits.length; i++)
			answers[i] = hiddenDigits[i][2];
		return answers;
	}

	/**
	 * Returns the digit at the specified location on the numGrid.
	 * @param row the row of the digit
	 * @param col the column of the digit
	 * @return the Digit object at the specified row and column
	 */
	public Digit getDigit(int row, int col) {
		return numGrid[row][col];
	}

	/**
	 * Returns the Digits in a specified row of numGrid.
	 * @param index the index of the row
	 * @return a Digit array containing each Digit in the specified row
	 */
	public Digit[] getRow(int index) {
		return numGrid[index];
	}

	/**
	 * Returns the Digits in a specified column of alignedNumGrid.
	 * @param index the index of the column
	 * @return a Digit array containing each Digit in the specified column
	 */
	public Digit[] getCol(int index) {
		Digit[] column = new Digit[alignedNumGrid.length]; // create empty integer array
		// for each digit at the specified column index of alignedNumGrid:
		// - set the next empty slot in the column array equal to that answer
		for (int i = 0; i < alignedNumGrid.length; i++)
			column[i] = alignedNumGrid[i][index];
		return column;
	}

	// debug
	public void printNumGrid() {
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

	// debug
	public static void main(String[] args) {
		ColumnLevel cl = new ColumnLevel(ColumnLevel.ADDITION);
		cl.printNumGrid();
		cl.addVariables();
		int[] answers = cl.getAnswers();
		Utils.printArray(answers);
		System.out.println(cl.fill(0, answers[0]));
	}
}

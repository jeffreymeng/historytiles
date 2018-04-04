/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Mar 27, 2018
 * Purpose: to manage the numbers in a column-format level
 */

package level;

import java.util.*;
import util.Utils;

public class ColumnLevel extends Level {

	private Digit[][] numGrid = { {/* first number */}, {/* second number */}, {/* third number */} };
	private int numVariables;
	private int[][] hiddenDigits; // effectively an array of 3-tuples in format {row, column, answer}
	 /*private int[] answers;
	private int[][] hiddenCoordinates; // effectively an array of 2-tuples recording the row and column of hidden digits */
	private char operation; // should be one of the final static variables below

	final static char ADDITION = '+';
	final static char SUBTRACTION = '-';
	final static char MULTIPLICATION = '*';
	final static char DIVISION = '/';

	public ColumnLevel() {
		this.operation = ADDITION;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		fillNumGrid(num1, num2);

		numVariables = getNumGridLength() / 3;

		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation) {
		this.operation = operation;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);
		

		if (operation == SUBTRACTION && num2 > num1)
			swapNums();
		else if (operation == ADDITION
				&& String.valueOf(num2).length() > String.valueOf(num1)
						.length())
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = getNumGridLength() / 3;

		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation, int numVariables) {
		this.operation = operation;
		this.numVariables = numVariables;
		num1 = Utils.randInt(1, 1000);
		num2 = Utils.randInt(1, 1000);

		if (operation == SUBTRACTION && num2 > num1)
			swapNums();
		else if (operation == ADDITION
				&& Utils.getDigits(num2) > Utils.getDigits(num1))
			swapNums();

		fillNumGrid(num1, num2);

		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation, int num1, int num2) {
		this.operation = operation;
		this.num1 = num1;
		this.num2 = num2;
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = getNumGridLength() / 3;

		hiddenDigits = new int[numVariables][3];
	}

	public ColumnLevel(char operation, int num1min, int num1max, int num2min,
			int num2max) {
		this.operation = operation;
		num1 = Utils.randInt(num1min, num1max);
		num2 = Utils.randInt(num2min, num2max);
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();

		fillNumGrid(num1, num2);

		numVariables = getNumGridLength() / 3;

		hiddenDigits = new int[numVariables][3];
	}

	public void swapNums() {
		int temp = num1;
		num1 = num2;
		num2 = temp;
	}

	public void fillNumGrid(int num1, int num2) {
		String num1String = String.valueOf(num1); // converts num1 to string
		numGrid[0] = new Digit[num1String.length()];
		for (int i = 0; i < num1String.length(); i++) {
			numGrid[0][i] = new Digit(Character.getNumericValue(num1String
					.charAt(i)), true);
			// splits num1String into individual digits
		}

		String num2String = String.valueOf(num2);
		numGrid[1] = new Digit[num2String.length()];
		for (int i = 0; i < num2String.length(); i++)
			numGrid[1][i] = new Digit(Character.getNumericValue(num2String
					.charAt(i)), true);
		// splits num2String into individual digits
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
		if (operation == SUBTRACTION && this.num2 > this.num1)
			swapNums();
	}

	public int getNumGridLength() {
		int sum = 0;
		for (int i = 0; i < numGrid.length; i++)
			sum += numGrid[i].length;
		return sum;
	}

	public void printNumGrid() { // for debugging
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
	
	public void addVariables() {
		int randRowIndex;
		int randColIndex;
		for (int i = 0; i < numVariables; i++) {
			randRowIndex = Utils.randInt(0, numGrid.length);
			randColIndex = Utils.randInt(0, numGrid[1].length);
			hiddenDigits[i][0] = randRowIndex;
			hiddenDigits[i][1] = randColIndex;
			hiddenDigits[i][2] = numGrid[randRowIndex][randColIndex].getValue();
			/*answers[i] = numGrid[randRowIndex][randColIndex].getValue();
			hiddenCoordinates[i][0] = randRowIndex;
			hiddenCoordinates[i][1] = randColIndex;*/
			numGrid[randRowIndex][randColIndex].setVisible(false);
		}
		
		for (int i = 0; i < hiddenDigits.length; i++)
			System.out.println(hiddenDigits[i][0] + " " + hiddenDigits[i][1] + " " + hiddenDigits[i][2]);

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
		
		//Arrays.sort(hiddenDigits); // sorts
		
		for (int i = 0; i < hiddenDigits.length; i++)
			System.out.println(hiddenDigits[i][0] + "  " + hiddenDigits[i][1] + "  " + hiddenDigits[i][2]);
		
		/*Arrays.sort(answers);
		Arrays.sort(hiddenCoordinates);
		for (int i = 0; i < hiddenCoordinates.length; i++)
			Arrays.sort(hiddenCoordinates[i]);*/

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

	public static void main(String[] args) {
		ColumnLevel cl = new ColumnLevel(ColumnLevel.ADDITION);
		cl.printNumGrid();
		cl.addVariables();
	}

}

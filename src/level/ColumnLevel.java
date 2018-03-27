package level;

import util.Utils;

public class ColumnLevel extends Level {
	
	private int num1;
	private int num2;
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
	
}

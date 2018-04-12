/*
 * Programmers: Jeffrey Meng and Dylan Yang
 * Date: Apr 4, 2018
 * Purpose: TODO
 */

package level;

public class Operator {
	private char operation;
	
	final static char ADDITION = '+';
	final static char SUBTRACTION = '-';
	final static char MULTIPLICATION = '*';
	final static char DIVISION = '/';
	
	public Operator(char operation) {
		this.operation = operation;
	}
	
	public char getOperation() {
		return operation;
	}
	
	public void setOperation(char operation) {
		this.operation = operation;
	}
	
	public String toString() {
		return Character.toString(operation);
	}
}

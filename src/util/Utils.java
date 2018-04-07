package util;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
	
	/**
	 * @param min the minimum number to be generated (inclusive)
	 * @param max the maximum number to be generated (exclusive)
	 * @return a random number between min and max
	 */
	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	/**
	 * @param number the number to return the number of digits of
	 * @return the number of digits in number
	 */
	public static int getDigits(int number) {
		return (int)(Math.log10(number)+1);
		//basically, log10 returns log base 10 of the number. Our number isn't exactly a multiple of 10(probably), so we cast it as an int.
	}
	
	/**
	 * Finds the nth digit of a number
	 * @param number the number to find the nth digit of
	 * @param digit  the index of the digit
	 * @return the nth digit in of the number
	 */
	public static int getDigit(int number, int digit) {
		
		// The nth digit of a number is (the remainder of the number divided by 10^n) divided by 10^n-1.
		// Note that in this case each digit of a number is indexed from right to left.
		// return (int) ( (number % Math.pow(10.0, (double)digit)) / Math.pow(10.0, (double)digit - 1) );
		
		// To find the nth digit of a number, first truncate it by n digits so that the last digit is the one requested.
		// Then find the remainder of dividing by 10, which gives the last digit
		// Note that each digit of a number is indexed from right to left, starting at 0.
		for (int i = 0; i < digit; i++)
			number /= 10; // effectively truncates number by one digit due to integer division
		
		return number % 10;
		
		// return 0;//for now
	}
	
	/**
	 * @param num the array to be printed
	 */
	public static void printArray(int[] num) {

		System.out.print("[");
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i]);
			if (i < num.length - 1)
				System.out.print(", ");
		}
		System.out.print("]");

	}
	
	// debugging tests
	public static void main(String[] args) {
		System.out.println(getDigit(12345, 0));
		System.out.println(getDigit(12345, 1));
		System.out.println(getDigit(12345, 2));
		System.out.println(getDigit(12345, 3));
		System.out.println(getDigit(12345, 4));
	}
}

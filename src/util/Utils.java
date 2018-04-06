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
	 * @param number
	 * @param digit
	 * @return the nth digit in number where n = digit
	 */
	public static int getDigit(int number, int digit) {
		//The nth digit is (the remainder of dividing by 10^n) divided by 10^n-1
		for (int i = 0; i < getDigits(number); i ++) {
			
		}
		
		return 0;//for now

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
}

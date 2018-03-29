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
	public static int getDigits(int number) {
		return (int)(Math.log10(number)+1);
		//basically, log10 returns log base 10 of the number. Our number isn't exactly a multiple of 10(probably), so we cast it as an int.
	}
	public static int getDigit(int number, int digit) {
		//The nth digit is (the remainder of dividing by 10^n) divided by 10^n-1
		for (int i = 0; i < getDigits(number); i ++) {
			
		}
		
		return 0;//for now

	}
}

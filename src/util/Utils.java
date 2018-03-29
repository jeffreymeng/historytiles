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
}

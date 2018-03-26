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

}

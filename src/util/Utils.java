package util;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

	// returns a random integer between min (inclusive) and max (exclusive)
	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	// returns the number of digits in a number
	public static int getDigits(int number) {
		return (int)(Math.log10(number)+1);
		//basically, log10 returns log base 10 of the number. Our number isn't exactly a multiple of 10(probably), so we cast it as an int.
	}

	// returns the nth digit of a number, indexed from right to left
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
	}

	// debugging
	public static void main(String[] args) {
		System.out.println(getDigit(12345, 0));
		System.out.println(getDigit(12345, 1));
		System.out.println(getDigit(12345, 2));
		System.out.println(getDigit(12345, 3));
		System.out.println(getDigit(12345, 4));
	}
}

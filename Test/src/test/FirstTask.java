package test;

import java.util.Scanner;

public class FirstTask {
	// count all correct bracket sequences for a number
	
	// a simple function for counting binomial coefficients
	public static int Ci(int n, int k) {
		double res = 1;
		for (int i = 1; i <= k; ++i) {
			res = res * (n - k + i) / i;
		}
		return (int) (res + 0.01);
	}
	// using formula for Catalan numbers to count our correct bracket sequence
	public static float find(int n) {
		float koef = (float) 1 / (n + 1);
		float dn = koef * (float) Ci(2 * n, n);
		return dn;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the number of correct bracket sequence: ");
		String input = scan.nextLine();
		if (!input.matches("-?\\d+(\\.\\d+)?")) {
			System.out.println("Input isn't valid. Try again");
		}
		//counting and printing the result in special format
		float dn = find(Integer.parseInt(input));
		System.out.format("The answer is: %10.0f%n", dn);
	}

}

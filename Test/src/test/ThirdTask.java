package test;

import java.math.BigInteger;

public class ThirdTask {
	// count the sum of the digits in the number 100!

	// a simple function to count factorial using BigInteger, because 100! is too big fir int
	static BigInteger factorial(int n) {
		BigInteger ret = BigInteger.ONE;
		for (int i = 1; i <= n; ++i)
			ret = ret.multiply(BigInteger.valueOf(i));
		return ret;
	}

	//counting the sum using loop
	public static void main(String[] args) {
		String ret = "" + factorial(100);
		System.out.println(ret);
		int result = 0;
		for (int i = 0; i < ret.length(); i++) {
			result += Integer.parseInt(ret.substring(i, i + 1));
		}
		System.out.println("the answer is:" + result);

	}

}

package baeckjoon.silver;

import java.math.BigInteger;
import java.util.Scanner;

public class SugarDeliveryTwo26099 {

	static final BigInteger INF = BigInteger.valueOf((long) Math.pow(10, 18));
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger N = sc.nextBigInteger();
		
		BigInteger[] dp = new BigInteger[N.intValue()+1];
		dp[0] = BigInteger.valueOf(0);
		dp[1] = BigInteger.valueOf(0);
		dp[2] = BigInteger.valueOf(0);

		for (int i=3; i<N.intValue()+1; i++) {
			BigInteger t3 = i % 3 == 0 ? BigInteger.valueOf(i / 3) : INF;
			BigInteger t5 = i % 5 == 0 ? BigInteger.valueOf(i / 5) : INF;
			BigInteger d3 = i > 6 ? dp[i-3].add(BigInteger.ONE) : INF;
			BigInteger d5 = i > 10 ? dp[i-5].add(BigInteger.ONE) : INF;
			
			BigInteger min = t3.min(t5).min(d3).min(d5);
			
			dp[i] = min;
		}
		
//		for (int i : dp)
//			System.out.print(i + " ");
		
		System.out.println(dp[N.intValue()].equals(INF) ? -1 : dp[N.intValue()]);
		sc.close();
	}
}

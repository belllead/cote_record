package baeckjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBStreet1149 {

	static int N;
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][3];
		for (int i=0; i<3; i++) {
			dp[0][i] = cost[0][i];
		}
		
		for (int i=1; i<N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
		}

		int min = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
		
//		for (int[] i: dp)
//			System.out.println(Arrays.toString(i));
		
		System.out.println(min);
		
	}
	
}

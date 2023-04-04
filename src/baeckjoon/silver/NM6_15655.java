package baeckjoon.silver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NM6_15655 {

	static int N, M;
	static List<String> list = new ArrayList<>();
	static int[] s, arr;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N+1];
		
		for (int i=1; i<N+1; i++)
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr);
		
		s = new int[M];
		visited = new boolean[N+1];
		
		powerSet(0, 0);
		
		System.out.println(sb);
		sc.close();
	}
	
	static void powerSet(int idx, int sidx) {
		if (sidx == M) {
			for (int i : s)
				sb.append(i).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i=idx+1; i<=N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				s[sidx] = arr[i];
				powerSet(i, sidx+1);
				visited[i] = false;
			}
		}
			
	
	}
}

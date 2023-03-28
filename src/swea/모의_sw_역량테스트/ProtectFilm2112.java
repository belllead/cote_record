package swea.모의_sw_역량테스트;

import java.util.Arrays;
import java.util.Scanner;

public class ProtectFilm2112 {
	
	static int D, W, K, min;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			
			int[][] film = new int[D][W];
			visited = new boolean[D];
			
			for (int r=0; r<D; r++) {
				for (int c=0; c<W; c++) {
					film[r][c] = sc.nextInt();
				}
			}
			System.out.println(check(film));
			
			
			sb.append("#" + tc + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static int treat(int[][] film, int n) {
		if (check(film))
			return n;
		
		if (n == K) 
			return n;
		
		for (int row=0; row<D; row++) {
			if (!visited[row]) {
				int[] temp = Arrays.copyOf(film[row], film[row].length);
				
				Arrays.fill(film[row], 1);
				treat(film, n+1);
				
				Arrays.fill(film[row], 0);
				treat(film, n+1);
				
				film[row] = temp;
			}
				
		}
		
		return 0;
	}
	
	
	static boolean check(int[][] film) {
		for (int c=0; c<W; c++) {
			int prev = film[0][c];
			int cnt = 1;
			for (int r=1; r<D; r++) {
				if (film[r][c] == prev) {
					cnt++;
				} else {
					prev = film[r][c];
					cnt = 1;
				}
				
				if (cnt >= K) break;
				
				if (r == D-1 && cnt < K) {
					return false;
				}
			}
		}
		
		return true;
	}
}

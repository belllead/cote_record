package swea.모의_sw_역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DesertCafe2105_3 {

	static int N, startR, startC, max;
	static int[] dr = {-1, -1, 1, 1}, dc = {-1, 1, 1, -1};
	static int[][] cafe;
	static boolean[][] visited;
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			cafe = new int[N][N];
			
			for (int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c=0; c<N; c++) {
					cafe[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for (int r=0; r<N; r++) {
//				for (int c=0; c<N; c++) {
//					System.out.printf("%3d", cafe[r][c]);
//				}
//				System.out.println();
//			}
			
			max = -1;
			
			outer: for (int i=N; i>=3; i--) {
				for (int r=0; r<=N-i; r++) {
					for (int c=0; c<=N-i; c++) {
//						System.out.println("size: " + i);
//						System.out.println(isPossibleRoute(r, c, i));
						if (isPossibleRoute(r, c, i)) {
							max = 2*(i-1); 
							break outer;
						}
					}
				}
			}
			
			sb.append("#" + tc + " ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isPossibleRoute(int row, int col, int size) {
		int[][] test = new int[size][size];
		
		for (int r=0; r<size; r++) {
			for (int c=0; c<size; c++) {
				test[r][c] = cafe[row+r][col+c];
			}
		}
		
//		for (int r=0; r<size; r++) {
//			for (int c=0; c<size; c++) {
//				System.out.printf("%3d", test[r][c]);
//			}
//			System.out.println();
//		}
		

		for (int i=1; i<=size-2; i++) {
//			System.out.println();
//			System.out.println("i: " + i);
//			System.out.println();
			set = new HashSet<>();
			boolean flag = true;
			
			outer: for (int r=0; r<size; r++) {
				for (int c=0; c<size; c++) {
					
					if (Math.abs(r-c) == i) {
//						System.out.println(r + " " + c + " " + test[r][c]);
						if (set.contains(test[r][c])) {
							flag = false;
							break outer;
						}
						else set.add(test[r][c]);
					}
					
					else {
						if (r+c == i || r+c == 2*(size-1)-i) {
//							System.out.println(r + " " + c + " " + test[r][c]);
							if (set.contains(test[r][c])) {
								flag = false;
								break outer;
							}
							else set.add(test[r][c]);
						}
					}
				}
			}
			
			if (flag) return true;
		}
		return false;
	}
	
}






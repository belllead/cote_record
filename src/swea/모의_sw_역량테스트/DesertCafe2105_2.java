package swea.모의_sw_역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DesertCafe2105_2 {

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
			
			max = -1;
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					visited = new boolean[N][N];
					set = new HashSet<>();
					startR = r;
					startC = c;
					
//					System.out.println("row: " + r);
//					System.out.println("col: " + c);
					
					dfs(r, c, 1);
				}
			}
			
			sb.append("#" + tc + " ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	static void dfs(int row, int col, int cnt) {
		visited[row][col] = true;
		set.add(cafe[row][col]);
		
//		System.out.println("tr: " + row);
//		System.out.println("tc: " + col);
//		System.out.println("cnt: " + cnt);
//		System.out.println();
		
		for (int i=0; i<4; i++) {
			int tr = row + dr[i]; 
			int tc = col + dc[i];
			
			if (cnt != 2 && tr == startR && tc == startC) {
//				System.out.println("comeback: " + cnt);
//				System.out.println();
				max = Math.max(max, cnt);
				if (cnt == 10) {
					System.out.println(startR);
					System.out.println(startC);
				}
			}
			
			if (tr >= 0 && tr < N && tc >= 0 && tc < N 
					&& !visited[tr][tc] && !set.contains(cafe[tr][tc])) {
				
				dfs(tr, tc, cnt+1);
				
				// 원상 복귀
				visited[tr][tc] = false;
				set.remove(cafe[tr][tc]);
			}
		}
		
	}
	
}






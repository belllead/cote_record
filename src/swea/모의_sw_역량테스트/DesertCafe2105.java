package swea.모의_sw_역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class DesertCafe2105 {

	static int N, startR, startC, max;
	static int[] dr = {-1, -1, 1, 1}, dc = {-1, 1, 1, -1};
	static int[][] cafe;
	static boolean[][] visited;
	
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
			
			max = 0;
			
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					startR = r;
					startC = c;
					
					visited = new boolean[N][N];
					System.out.println("row: " + r);
					System.out.println("col: " + c);
					
					for (int i=0; i<4; i++) {
						int tempR = r + dr[i]; 
						int tempC = c + dc[i];
						
						if (tempR >= 0 && tempR < N && tempC >= 0 && tempC < N)
							bfs(tempR, tempC);
					}
				}
			}
			
			sb.append("#" + tc + " ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	static int bfs(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		
		q.offer(new int[] {row, col});
		set.add(cafe[row][col]);
		
		int cnt = 0;
		
		visited[row][col] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			cnt++;
			
			while (size-- > 0) {
				int[] tCoord = q.poll();
				
				
				
				for (int i=0; i<4; i++) {
					int tr = tCoord[0] + dr[i];
					int tc = tCoord[1] + dc[i];
					
					if (tr == startR && tc == startC)
						return cnt;
					
					if (tr >= 0 && tr < N && tc >= 0 && tc < N 
							&& !visited[tr][tc] && !set.contains(cafe[tr][tc])) {
						q.offer(new int[] {tr, tc});
						set.add(cafe[tr][tc]);
						visited[tr][tc] = true;
					}
				}
			}
			System.out.println(q);
		}
		return -1;
	}
	
}



























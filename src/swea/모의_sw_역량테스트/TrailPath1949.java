package swea.모의_sw_역량테스트;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrailPath1949 {

	static int N, K, max, dig;
	static int[][] mount;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();

			mount = new int[N][N];

			int peak = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					mount[r][c] = sc.nextInt();
					peak = Math.max(peak, mount[r][c]);
				}
			}

			List<int[]> peakList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (mount[r][c] == peak)
						peakList.add(new int[] { r, c });
				}
			}

			max = 0;
			visited = new boolean[N][N];
			
			// 입력완료
			
//			for (int i=1; i<=K; i++) {
//				dig = i;
//				for (int[] p : peakList)
//					bfs(p[0], p[1]);
//			}
			
			for (int[] p : peakList) {
				visited[p[0]][p[1]] = true;
				dfs(p[0], p[1], 1, 0, mount);
				visited[p[0]][p[1]] = false;
			}

			sb.append("#" + tc + " ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
		sc.close();
	}

//	private static void bfs(int row, int col) {
//		Queue<int[]> q = new LinkedList<>();
//		boolean[][][] visited = new boolean[N][N][2];
//		
//		q.offer(new int[] {row, col, 0});
//		visited[row][col][0] = true;
//		
//		int cnt = 0;
//		
//		while (!q.isEmpty()) {
//			int size = q.size();
//		
//			cnt++;
//
//			for (int[] l : q)
//				System.out.println(Arrays.toString(l));
//			System.out.println();
//			while (size-- > 0) {
//				int[] curr = q.poll();
//				
//				for (int i=0; i<4; i++) {
//					int tr = curr[0] + dr[i];
//					int tc = curr[1] + dc[i];
//					int dug = curr[2];
//					
//					// 경계 안에서
//					if (tr >= 0 && tr < N && tc >= 0 && tc < N) {
//						// 공사한 적이 없다면
//						if (dug == 0) {
//							// 공사 안 한 갈래로 보내보고
//							if (!visited[tr][tc][dug] && mount[tr][tc] < mount[curr[0]][curr[1]]) {
//								q.offer(new int[] {tr, tc, dug});
//								visited[tr][tc][dug] = true;
//							}
//							
//							// 공사 하고 보내본다
//							mount[tr][tc] -= dig;
//							if (!visited[tr][tc][dug+1] && mount[tr][tc] < mount[curr[0]][curr[1]]) {
//								q.offer(new int[] {tr, tc, dug+1});
//								visited[tr][tc][dug+1] = true;
//							}
//							mount[tr][tc] += dig;
//						} else { // 공사한 적이 있다면
//							if (!visited[tr][tc][dug] && mount[tr][tc] < mount[curr[0]][curr[1]]) {
//								q.offer(new int[] {tr, tc, dug});
//								visited[tr][tc][dug] = true;
//							}
//						}
//					}
//				}
//			}
//		}
//		max=Math.max(max,cnt);
//	}

	private static void dfs(int row, int col, int cnt, int dug, int[][] mount) {
		max=Math.max(max, cnt);
		
		for (int i=0; i<4; i++) {
			int tr = row + dr[i];
			int tc = col + dc[i];
			
			// 경계 안에서
			if (tr >= 0 && tr < N && tc >= 0 && tc < N) {
				// 공사한 적이 없다면
				if (dug == 0) {
					// 공사 안 한 갈래로 보내보고
					if (!visited[tr][tc] && mount[tr][tc] < mount[row][col]) {
						visited[tr][tc] = true;
						dfs(tr, tc, cnt+1, dug, mount);
						visited[tr][tc] = false;
					}
					
					// 공사 하고 보내본다
					for (int dig=1; dig<=K; dig++) {
						mount[tr][tc] -= dig;
						if (!visited[tr][tc] && mount[tr][tc] < mount[row][col]) {
							visited[tr][tc] = true;
							dfs(tr, tc, cnt+1, dug+1, mount);
							visited[tr][tc] = false;
						}
						mount[tr][tc] += dig;
					}
				} else { // 공사한 적이 있다면
					if (!visited[tr][tc] && mount[tr][tc] < mount[row][col]) {
						visited[tr][tc] = true;
						dfs(tr, tc, cnt+1, dug, mount);
						visited[tr][tc] = false;
					}
				}
			}
		}
	}
	
}

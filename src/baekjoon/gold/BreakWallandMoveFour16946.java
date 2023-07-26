package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallandMoveFour16946 {

	static int N, M;
	static int[][] map, rst;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		rst = new int[N][M];

		char[] tchar = new char[M];

		int[] start = new int[2];
		
		for (int r = 0; r < N; r++) {
			tchar = br.readLine().toCharArray();
			
			for (int c = 0; c < M; c++) {
				map[r][c] = tchar[c] - '0';
				
				if (map[r][c] == 1) {
					start[0] = r;
					start[1] = c;
				}
			}
		}

		bfs(start[0], start[1]);
		
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(rst[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void bfs(int r, int c) {
		Queue<int[]> wallQ = new LinkedList<>();
		Queue<int[]> roadQ = new LinkedList<>();

		boolean[][][] visited = new boolean[2][N][M];
		
		int cnt = 0;

		wallQ.offer(new int[] {r, c});
		visited[0][r][c] = true;
		
		while (!wallQ.isEmpty()) {
			int[] currWall = wallQ.poll();
			
			// 방문배열 초기화
			visited[1] = new boolean[N][M];
			
			roadQ.offer(currWall);
			visited[1][currWall[0]][currWall[1]] = true;
			
			cnt = 0;
			
			while (!roadQ.isEmpty()) {
				int[] curr = roadQ.poll();
				
				cnt++;
				
				for (int i = 0; i < 4; i++) {
					int tr = curr[0] + dr[i];
					int tc = curr[1] + dc[i];
					
					if (tr >= 0 && tr < N && tc >= 0 && tc < M) {
						// 빈 칸일 때
						if (map[tr][tc] == 0 && !visited[1][tr][tc]) {
							roadQ.offer(new int[] { tr, tc });
							visited[1][tr][tc] = true;
						}
						
						// 벽일 때
						if (map[tr][tc] == 1 && !visited[0][tr][tc]) {
							wallQ.offer(new int[] { tr, tc });
							visited[0][tr][tc] = true;
						}
					}
				}
				
			}
			rst[currWall[0]][currWall[1]] = cnt;
		}
	}
}























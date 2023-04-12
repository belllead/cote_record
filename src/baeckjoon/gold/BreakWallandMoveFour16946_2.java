package baeckjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallandMoveFour16946_2 {

	static int N, M;
	static int[][] map, empty;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		empty = new int[N][M];
		int[][] rst = new int[N][M];

		char[] tchar = new char[M];

		for (int r = 0; r < N; r++) {
			tchar = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = tchar[c] - '0';
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0 && empty[r][c] == 0) {
					bfs(r, c);
				}
			}
		}

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				System.out.print(empty[r][c]);
//			}
//			System.out.println();
//		}
//		System.out.println();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1) {
					rst[r][c] = 1;
					
					for (int i = 0; i < 4; i++) {
						int tr = r + dr[i];
						int tc = c + dc[i];

						if (tr >= 0 && tr < N && tc >= 0 && tc < M) {
							rst[r][c] += empty[tr][tc];
						}
					}
				}
			}
		}

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
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		q.offer(new int[] { r, c });
		visited[r][c] = true;

		int cnt = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			cnt++;
			empty[r][c] = cnt % 10;

			for (int i = 0; i < 4; i++) {
				int tr = curr[0] + dr[i];
				int tc = curr[1] + dc[i];

				if (tr >= 0 && tr < N && tc >= 0 && tc < M) {
					if (map[tr][tc] == 0 && !visited[tr][tc]) {
						q.offer(new int[] { tr, tc });
						visited[tr][tc] = true;
					}
				}
			}
		}
	}
}

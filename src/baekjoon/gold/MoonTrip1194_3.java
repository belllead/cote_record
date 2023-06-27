package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoonTrip1194_3 {

	static int N, M, min;
	static char[][] maze;
	static List<int[]> exit;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 }, keyOK = { 0, 0, 0, 0, 0, 0 };
	static List<int[]>[] keyPos = new ArrayList[6];
	static char[] key = {'a', 'b', 'c', 'd', 'e', 'f'}, lock = {'A', 'B', 'C', 'D', 'E', 'F'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new char[N][M];

		for (int r = 0; r < N; r++) {
			maze[r] = br.readLine().toCharArray();
		}

//		for (char[] c : maze)
//			System.out.println(Arrays.toString(c));

		int[] start = new int[2];
		exit = new ArrayList<>();
		
		for (int i=0; i<6; i++)
			keyPos[i] = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (maze[r][c] == '0') {
					start[0] = r;
					start[1] = c;
				}

				if (maze[r][c] == '1') {
					exit.add(new int[] { r, c });
				}
				
				for (int i=0; i<6; i++) {
					if (maze[r][c] == lock[i]) {
						keyPos[i].add(new int[] {r, c});
					}
				}
					
			}
		}

		// 입력 완료
		
		min = Integer.MAX_VALUE;

		bfs(start[0], start[1], -1);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void bfs(int row, int col, int cnt) {
//		for (char[] c : maze)
//			System.out.println(Arrays.toString(c));
		
		Queue<int[]> q = new LinkedList<>();
		int[] visited = new int[N];
		Arrays.fill(visited, 0<<M);

		q.offer(new int[] { row, col });
		visited[row] = visited[row] ^ (1<<(M-1-col));

		while (!q.isEmpty()) {
			int size = q.size();

			cnt++;

			while (size-- > 0) {
				int[] curr = q.poll();

				// 현재 위치가 열쇠면 자물쇠 문 열고 이 자리에서 출구 찾기
				for (int i=0; i<6; i++) {
					if (maze[curr[0]][curr[1]] == key[i]) {
						keyOK[i]++;
						
						maze[curr[0]][curr[1]] = '.';
						for (int[] k : keyPos[i])
							maze[k[0]][k[1]] = '.';
						
						bfs(curr[0], curr[1], cnt-1);
						
						for (int[] k : keyPos[i])
							maze[k[0]][k[1]] = lock[i];
						maze[curr[0]][curr[1]] = key[i];
					}
				}
					
				
				// 출구 만나면 최소거리 저장하고 이 단계의 탐색 종료
				for (int[] e : exit) {
					if (curr[0] == e[0] && curr[1] == e[1]) {
						min = Math.min(min, cnt);
						return;
					}
				}

				for (int i = 0; i < 4; i++) {
					int tr = curr[0] + dr[i];
					int tc = curr[1] + dc[i];

					// 경계 안에서 벽이 아니면
					if (tr >= 0 && tr < N && tc >= 0 && tc < M
							&& ((visited[tr] & (1<<(M-1-tc))) == 0)
							&& maze[tr][tc] != '#' 
							&& maze[tr][tc] != 'A'
							&& maze[tr][tc] != 'B'
							&& maze[tr][tc] != 'C'
							&& maze[tr][tc] != 'D'
							&& maze[tr][tc] != 'E'
							&& maze[tr][tc] != 'F') {
						// 계속 탐색
						q.offer(new int[] { tr, tc });
						visited[tr] = visited[tr] ^ (1<<(M-1-tc));
					}
				}
			}
		}
	}
}

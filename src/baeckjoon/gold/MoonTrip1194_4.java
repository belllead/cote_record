package baeckjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoonTrip1194_4 {

	static int N, M, min;
	static char[][] maze;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
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
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (maze[r][c] == '0') {
					start[0] = r;
					start[1] = c;
				}
			}
		}

		// 입력 완료
		
		min = Integer.MAX_VALUE;

		bfs(start[0], start[1], -1);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void bfs(int row, int col, int cnt) {
		Queue<int[]> q = new LinkedList<>();s
	}
	
}

























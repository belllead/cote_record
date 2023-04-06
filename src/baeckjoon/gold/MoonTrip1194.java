package baeckjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoonTrip1194 {

	static int N, M, min;
	static char[][] maze;
	static List<int[]> exit;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}, key = {0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		
		for (int r=0; r<N; r++) {
			maze[r] = br.readLine().toCharArray();
		}
		
		for (char[] c : maze)
			System.out.println(Arrays.toString(c));
		
		int[] start = new int[2];
		exit = new ArrayList<>();
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				if (maze[r][c] == '0') {
					start[0] = r;
					start[1] = c;
				}
				
				if (maze[r][c] == '1') {
					exit.add(new int[] {r, c});
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		
		bfs(start[0], start[1]);
		
		System.out.println(min == 0 ? -1 : min);
	}
	
	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {row, col});
		
		int cnt = -1;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			cnt++;
			
			while (size-- > 0) {
				int[] curr = q.poll();
				
				System.out.println(curr[0] + " " + curr[1]);
				System.out.println("cnt: " + cnt);
				System.out.println(Arrays.toString(key));
				
				for (int[] e : exit) {
					if (curr[0] == e[0] && curr[1] == e[1]) {
						min = Math.min(min, cnt);
						return;
					}
				}
				
				for (int i=0; i<4; i++) {
					int tr = curr[0] + dr[i];
					int tc = curr[1] + dc[i];
					
					// 경계 안에서
					if (tr >= 0 && tr < N && tc >= 0 && tc < M) {
						switch (maze[tr][tc]) {
						
						// 벽이면 가지 못함
						case '#': 
							break;
							
						// 열쇠면 콜렉트하고 이동
						case 'a':
							System.out.print("adfadsfasdf: " + tr + " " + tc);
							key[0]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
						case 'b':
							key[1]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
						case 'c':
							key[2]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
						case 'd':
							key[3]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
						case 'f':
							key[4]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
						case 'g':
							key[5]++;
							maze[tr][tc] = '.';
							q.offer(new int[] {tr, tc});
							break;
							
						// 자물쇠면 열쇠 여부에 따라 이동할 수 있음
						case 'A':
							if (key[0] == 1) q.offer(new int[] {tr, tc});
							break;
						case 'B':
							if (key[1] == 1) q.offer(new int[] {tr, tc});
							break;
						case 'C':
							if (key[2] == 1) q.offer(new int[] {tr, tc});
							break;
						case 'D':
							if (key[3] == 1) q.offer(new int[] {tr, tc});
							break;
						case 'E':
							if (key[4] == 1) q.offer(new int[] {tr, tc});
							break;
						case 'F':
							if (key[5] == 1) q.offer(new int[] {tr, tc});
							break;
							
						// 그 외 출구, 빈칸, 시작위치면 이동
						default:
							q.offer(new int[] {tr, tc});
						}
					}
				}
			}
		}
		
		min = Math.min(min, cnt);
	}
}



































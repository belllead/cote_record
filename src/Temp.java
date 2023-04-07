public class Temp {
	static Reader in = new Reader();
	static int[][] map, water;
	static int N, M, INF, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		INF = 10001;
		
		map = new int[N+2][M+2];
		water = new int[N+2][M+2];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				map[r][c] = in.nextInt();
				water[r][c] = INF;
			}
		}
		
		solve();
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				ans += water[r][c] - map[r][c];
			}
		}
		
		System.out.println(ans);
	}
	
	static void solve() {
		while(true) {
			boolean flag = true;
			
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= M; c++) {
					if(map[r][c] == water[r][c]) continue;
					
					int min = INF;
					
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						min = Math.min(min, water[nr][nc]);
					}
					
					if(water[r][c] > min) {
						water[r][c] = Math.max(map[r][c], min);
						flag = false;
					}
				}
			}
			
			if(flag) break;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
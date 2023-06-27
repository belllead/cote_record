package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACMCraft1005 {
	
	static int[] buildTime, inDegree;
	static boolean[] visited;
	static List<Integer>[] adjList;
	static List<Integer>[] bwdAdjList;
	static List<Integer> necessaryBuild = new ArrayList<>();
	static int N, W, totalTime;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			buildTime = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<N+1; i++)
				buildTime[i] = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			for (int i=0; i<N+1; i++)
				adjList[i] = new ArrayList<>();
			
			bwdAdjList = new ArrayList[N+1];
			for (int i=0; i<N+1; i++)
				bwdAdjList[i] = new ArrayList<>();
			
			inDegree = new int[N+1];
			visited = new boolean[N+1];
			
			while (K-- > 0) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				adjList[s].add(e);
				inDegree[e]++;

				bwdAdjList[e].add(s);
			}
			
			W = Integer.parseInt(br.readLine());
			//입력완료
			
			totalTime = 0;
			
			System.out.println(Arrays.toString(buildTime));
			
			findNecessaryBuild();
			System.out.println(necessaryBuild);
//			topoBFS();
			
			for (int i=0; i<N+1; i++) {
				if (!visited[i] && inDegree[i] == 0 && necessaryBuild.contains(i))
					topoDFS(i, buildTime[i]);
			}
			sb.append(totalTime + "\n");
		}
		System.out.println(sb);
	}
	
	static void topoDFS(int curr, int build) {
		if (!necessaryBuild.contains(curr)) return;
		
		System.out.println(curr);
		
		if (curr != W)
			visited[curr] = true;
		inDegree[curr]--;
		
		for (int v : adjList[curr]) {
			if (!visited[v]) {
				topoDFS(v, build + buildTime[v]);
			}
		}
		
		if (curr == W && inDegree[curr] == 0)
			totalTime = Math.max(totalTime, build);
		
		System.out.println(totalTime);
	}
	
	
	static void topoBFS() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int v=1; v<N+1; v++) {
			if (inDegree[v] == 0)
				q.offer(v);
		}
		
		int totalTime = 0;
		
		outer: while (!q.isEmpty()) {
			int size = q.size();

			int timeStep = 0;
			
			//depth 하나씩 처리
			while (size-- > 0) {
				int curr = q.poll();
				
				if (!necessaryBuild.contains(curr)) continue;
				
				// 이번 depth에 목표 건물이 있으면 그 건물 짓는 시간 더하고 반복 종료
				if (curr == W) {
					timeStep = buildTime[curr];
					totalTime += timeStep;
					break outer;
				} else { // 아니면 이번 단계에서 가장 짓는 데 오래걸리는 시간 더하기
					timeStep = Math.max(timeStep, buildTime[curr]);
				}
				
				for (int v : adjList[curr]) {
					inDegree[v]--;
					
					if (inDegree[v] == 0)
						q.offer(v);
				}
			} //size while
			System.out.println(timeStep);
			totalTime += timeStep;
		} // q while
		
		sb.append(totalTime + "\n");
	}
	
	static void findNecessaryBuild() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(W);
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			
			necessaryBuild.add(curr);
			
			for (int v : bwdAdjList[curr]) {
				if (!visited[v]) {
					q.offer(v);
					visited[v] = true;
				}
			}
		}
	}
}
















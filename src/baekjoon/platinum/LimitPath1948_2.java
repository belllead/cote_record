package baekjoon.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class LimitPath1948_2 {
	
	static int endCity, totalTime;
	static List<Node>[] adjList;
	static Set<Integer> runningRoad;
	static Set<Integer> runningRoadSet;
	static boolean[] visited;

	static class Node {
		int roadNum, city, w;

		public Node(int roadNum, int city, int w) {
			super();
			this.roadNum = roadNum;
			this.city = city;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [roadNum=" + roadNum + ", city=" + city + ", w=" + w + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		for (int i=0; i<N+1; i++) 
			adjList[i] = new ArrayList<>();
		
		int[] inDegree = new int[N+1];
		visited = new boolean[N+1];
		
		int idx = 1;
		
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[s].add(new Node(idx++, e, w));
			inDegree[e]++;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int startCity = Integer.parseInt(st.nextToken());
		endCity = Integer.parseInt(st.nextToken());
		
//		System.out.println(Arrays.toString(inDegree));
//		
//		for (List<Node> l : adjList)
//			System.out.println(l);

		// 입력완료
		
		runningRoad = new HashSet<>();
		runningRoadSet = new HashSet<>();
		
		findRunner(startCity, 0);
		
		System.out.println(totalTime);
		
//		System.out.println(runningRoadSet);
		System.out.println(runningRoadSet.size());
		
//		System.out.println(Arrays.toString(wait));
	}
	
	static void findRunner(int n, int time) {
		if (n == endCity) {
			totalTime = Math.max(totalTime, time);
			if (totalTime < time) {
				totalTime = time;
				runningRoadSet.clear();
				runningRoadSet.addAll(runningRoad);
			} else if (totalTime == time) {
				runningRoadSet.addAll(runningRoad);
			}
			return;
		}

		for (Node node : adjList[n]) {
			if (visited[node.city]) continue;
			
			runningRoad.add(node.roadNum);
			findRunner(node.city, time + node.w);
			runningRoad.remove(node.roadNum);
		}
	}
}






















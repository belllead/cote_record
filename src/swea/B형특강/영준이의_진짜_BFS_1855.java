package swea.B형특강;

import java.io.*;
import java.util.*;

public class 영준이의_진짜_BFS_1855 {

	static class Node {
		int nodeNum;
		int parent;
		List<Integer> children;
		
		public Node(int nodeNum) {
			this.nodeNum = nodeNum;
			this.children = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Node [parent=" + parent + ", children=" + children + "]";
		}
	}
	
	static Node[] tree;
	static int N;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			tree = new Node[N+1];
			for (int i=1; i<=N; i++) {
				tree[i] = new Node(i);
			}
			
			answer = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=2; i<=N; i++) {
				int p = Integer.parseInt(st.nextToken());
				tree[i].parent = p;
				tree[p].children.add(i);
			}
			
			bfs();
//			System.out.println(Arrays.toString(tree));
//			System.out.println(findShortest(3, 4));
			
			
			sb.append("#").append(tc).append(" ");
			sb.append(answer);
			sb.append("\n");
		} // test case end
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(tree[1]);
		visited[1] = true;
		Node last = tree[1];
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
//			System.out.println("last: " + last.nodeNum);
//			System.out.println("cur: " + cur.nodeNum);
//			System.out.println(cur);
			answer += findShortest(last.nodeNum, cur.nodeNum);
			
			for (int child : cur.children) {
				if (!visited[child]) {
					q.offer(tree[child]);
					visited[child] = true;
				}
			}
			
			last = cur;
		}
	}
	
	static int findShortest(int nodeNum1, int nodeNum2) {
		
		if (nodeNum1 == nodeNum2) return 0;
		
		int dist = -1;
		
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.offer(tree[nodeNum1]);
		visited[nodeNum1] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			dist++;
			
			while (size-- > 0) {
				Node cur = q.poll();
				
				// 부모 쪽 탐색
				if (cur.nodeNum != 1 && !visited[cur.parent]) {
					
					if (cur.parent == nodeNum2) {
						return ++dist;
					}
					
					q.offer(tree[cur.parent]);
					visited[cur.parent] = true;
				}
				
				// 자식 쪽 탐색
				for (int child : cur.children) {
					
					if (child == nodeNum2) {
						return ++dist;
					}
					
					q.offer(tree[child]);
					visited[child] = true;
				}
			}
			
		}
		
		return 0;
	}
}
























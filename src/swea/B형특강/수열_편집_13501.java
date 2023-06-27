package swea.B형특강;

import java.io.*;
import java.util.*;

public class 수열_편집_13501 {

	static int MAX_NODE = 2050;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int data;
		Node next;
		
		public Node(int data) {
			super();
			this.data = data;
			this.next = null;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + ", next=" + next + "]";
		}
	}
	
	static class LinkedList {
		Node head;
		Node[] nodePool;
		int nodeCnt;
		int size;
		
		private LinkedList() {
			nodePool = new Node[MAX_NODE];
			nodeCnt = 0;
			size = 0;
			head = getNewNode(-1);
		}
		
		private Node getNewNode(int data) {
			nodePool[nodeCnt] = new Node(data);
			return nodePool[nodeCnt++];
		}
		
		private void insert(int idx, int data) {
			Node cur = head;
			
			if (idx == size) {
				for (int i=0; i<idx; i++) {
					cur = cur.next;
				}
				
				Node newNode = getNewNode(data);
				cur.next = newNode;
			}
			
			else {
				for (int i=0; i<idx; i++) {
					cur = cur.next;
				}
				
				Node newNode = getNewNode(data);
				newNode.next = cur.next;
				cur.next = newNode;
			}
			
			size++;
		}
		
		private void delete(int idx) {
			Node cur = head;
			for (int i=0; i<idx; i++) {
				cur = cur.next;
			}
			
			cur.next = cur.next.next;
			size--;
		}
		
		private void change(int idx, int data) {
			Node cur = head;
			for (int i=0; i<idx; i++) {
				cur = cur.next;
			}
			
			cur.next.data = data;
		}
		
		private void print(int idx) throws IOException {
			Node cur = head;
			for (int i=0; i<idx; i++) {
				cur = cur.next;
			}

			sb.append(cur.data);
		}

		@Override
		public String toString() {
			return "LinkedList [head=" + head + ", size=" + size + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			sb.append("#" + tc + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			LinkedList list = new LinkedList();
			
			int t = 0;
			for (int i=0; i<N; i++) {
				t = Integer.parseInt(st.nextToken());
				
				list.insert(i, t);
			}
			
			while (M-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				String op = st.nextToken();
				switch (op) {
				case "I":
					int idxI = Integer.parseInt(st.nextToken());
					int dataI = Integer.parseInt(st.nextToken());

					list.insert(idxI, dataI);
					break;
				case "D":
					int idxD = Integer.parseInt(st.nextToken());
					
					list.delete(idxD);
					break;
				case "C":
					int idxC = Integer.parseInt(st.nextToken());
					int dataC = Integer.parseInt(st.nextToken());
					
					list.change(idxC, dataC);
					break;
				}
			}
			
			if (list.size <= L) sb.append("-1");
			else list.print(L+1);
			
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}
}

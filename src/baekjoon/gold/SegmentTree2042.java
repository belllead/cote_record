package baekjoon.gold;

import java.util.Scanner;

public class SegmentTree2042 {

	static long[] arr, segmentTree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		arr = new long[N];
		segmentTree = new long[N*4];
		
		for (int i=0; i<N; i++)
			arr[i] = sc.nextLong();

		init(0, N-1, 1);
//		System.out.println(Arrays.toString(segmentTree));
		// 입력 완료
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<M+K; i++) {
			int order = sc.nextInt();
//			System.out.println("order: " + order);
			switch (order) {
			case 1:
				int targetIdx = sc.nextInt()-1;
				long newNum = sc.nextLong();
				long diff = newNum - arr[targetIdx];
				arr[targetIdx] = newNum;
//				System.out.println("arr[targetIdx]: " + arr[targetIdx]);
//				System.out.println("newNum: " + newNum);
//				System.out.println("diff : " + diff);
				updateTree(0, N-1, 1, targetIdx, diff);
				break;
			case 2:
				int first = sc.nextInt()-1;
				int last = sc.nextInt()-1;
				long sum = sumTree(0, N-1, 1, first, last);
				sb.append(sum);
				sb.append("\n");
				break;
			}
//			System.out.println("task " + (i+1) + ": " + Arrays.toString(segmentTree));
		}
		
		System.out.println(sb);
		sc.close();
	}

	private static long sumTree(int start, int end, int node, int first, int last) {
		if (first > end || last < start) return 0;
		
		if (first <= start && end <= last) return segmentTree[node];
		
		int mid = (start + end) / 2;

		return sumTree(start, mid, node*2, first, last) + sumTree(mid+1, end, node*2+1, first, last);
	}

	private static void updateTree(int start, int end, int node, int targetIdx, long diff) {
		if (start <= targetIdx && targetIdx <= end) {
			segmentTree[node] += diff;
		}
		
		if (start == end) return;
		
		int mid = (start + end) / 2;
		updateTree(start, mid, node*2, targetIdx, diff);
		updateTree(mid+1, end, node*2+1, targetIdx, diff);
	}

	private static long init(int start, int end, int node) {
		if (start == end) {
			segmentTree[node] = arr[start];
			return segmentTree[node];
		}
		
		int mid = (start + end) / 2;
		segmentTree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
		return segmentTree[node];
	}
}






















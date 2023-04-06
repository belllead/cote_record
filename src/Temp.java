import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Temp {
	static int score, currentR, currentC, N;
	static int[] currentDirection;
	static int[][] ballDirection = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] board, wormHole;
	static List<int[]> possibleStartPosition;
	
	public static void main(String[] args) throws IOException {

		int[] visited2 = new int[5];
		Arrays.fill(visited2, 0<<5);
		
		System.out.println(Arrays.toString(visited2));
		
		visited2[2] = visited2[2] ^ (1<<5);
		System.out.println(Arrays.toString(visited2));
		
		int visited = 0;
		visited = visited ^ (1<<5);
		System.out.println(visited);
	}
}
		
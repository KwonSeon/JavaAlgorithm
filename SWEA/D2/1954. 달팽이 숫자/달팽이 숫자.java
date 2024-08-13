import java.util.*;
import java.io.*;

public class Solution {

	static final int[] dr = { 0, 1, 0, -1 };
	static final int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine().trim());

			int[][] snail = new int[n][n];
			makeSnail(1, snail, 0, 0, 0, n);
			sb.append("#").append(t).append('\n');
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(snail[i][j]).append(" ");
				}
				sb.append('\n');
			}
		}
		System.out.println(sb.toString());
	}

	public static void makeSnail(int index, int[][] snail, int direction, int currentR, int currentC, int n) {
		snail[currentR][currentC] = index;
		if (index == n*n)
			return;

		int newR = currentR + dr[direction];
		int newC = currentC + dc[direction];

		if (newR >= 0 && newR < n && newC >= 0 && newC < n && snail[newR][newC] == 0) {
			makeSnail(index + 1, snail, direction, newR, newC, n);
		} else {
			int newDirection = (direction + 1) % 4;
			int r = currentR + dr[newDirection];
			int c = currentC + dc[newDirection];
			makeSnail(index + 1, snail, newDirection, r, c, n);
		}

	}
}
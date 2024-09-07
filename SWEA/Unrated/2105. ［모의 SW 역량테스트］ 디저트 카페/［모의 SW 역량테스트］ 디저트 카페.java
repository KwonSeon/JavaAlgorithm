import java.io.*;
import java.util.*;

public class Solution {

	static int n, matrix[][], maxCnt, currentCnt, R, C;
	static boolean[] dessert;
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			matrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxCnt = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dessert = new boolean[101];
					R = i;
					C = j;
					dfs(i, j, 0, 0);
				}
			}

			sb.append("#").append(t).append(" ").append(maxCnt).append('\n');
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int r, int c, int direction, int cnt) {

		if (r < 0 || r >= n || c < 0 || c >= n)
			return;

		if (r == R && c == C && cnt != 0) {
			maxCnt = Math.max(maxCnt, cnt);
			return;
		}

		if (dessert[matrix[r][c]])
			return;

		dessert[matrix[r][c]] = true;

		// 방향 그대로
		int nr = r + dr[direction];
		int nc = c + dc[direction];
		dfs(nr, nc, direction, cnt + 1);

		// 방향 꺾기
		// 시작하자마자 꺾이지 않게, 돌아오는 방향일 때 꺽이지 않게
		if (cnt != 0 && direction < 3) {
			nr = r + dr[direction + 1];
			nc = c + dc[direction + 1];
			dfs(nr, nc, direction + 1, cnt + 1);
		}

		dessert[matrix[r][c]] = false;
	}
}
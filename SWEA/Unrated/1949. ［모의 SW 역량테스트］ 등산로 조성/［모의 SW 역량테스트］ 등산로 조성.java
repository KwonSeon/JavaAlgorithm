import java.io.*;
import java.util.*;

public class Solution {

	static int n, k, map[][], highPeak, maxLength;
	static ArrayList<int[]> peaks;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 가장 높은 봉우리 갱신
			map = new int[n][n];
			highPeak = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					highPeak = Math.max(highPeak, map[i][j]);
				}
			}

			// 가장 높은 봉우리 위치 저장
			peaks = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == highPeak) {
						peaks.add(new int[] { i, j });
					}
				}
			}

			maxLength = 0;
			for (int[] p : peaks) {
				visited = new boolean[n][n];
				visited[p[0]][p[1]] = true;
				dfs(p[0], p[1], 1, highPeak, false);
			}

			sb.append("#").append(t).append(" ").append(maxLength).append('\n');
		}

		System.out.println(sb);
	}

	public static void dfs(int r, int c, int distance, int currentHigh, boolean dig) {

		maxLength = Math.max(maxLength, distance);

		for (int dir = 0; dir < 4; dir++) {

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				continue;

			if (visited[nr][nc])
				continue;

			// 현재 봉우리보다 작은 경우
			if (map[nr][nc] < currentHigh) {
				visited[nr][nc] = true;
				dfs(nr, nc, distance + 1, map[nr][nc], dig);
				visited[nr][nc] = false;
			} else {
				// 이미 공사를 한 경우
				if (dig)
					continue;
				// 공사 후 이동 가능한지 여부
				if (map[nr][nc] - k < currentHigh) {
					// 이동 가능한 가장 높은 높이
					visited[nr][nc] = true;
					dfs(nr, nc, distance + 1, currentHigh - 1, true);
					visited[nr][nc] = false;
				}
			}

		}

	}
}
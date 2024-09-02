import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, cntSum, cnt;
	static char[][] map;
	static int[][] distance;
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
			m = Integer.parseInt(st.nextToken());

			map = new char[n][m];
			distance = new int[n][m];
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < m; j++) {
					map[i][j] = line[j];
					if (map[i][j] == 'W') {
						q.add(new int[] { i, j });
					} else {
						distance[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			while (!q.isEmpty()) {
				int[] current = q.poll();
				int r = current[0];
				int c = current[1];

				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m || distance[nr][nc] != Integer.MAX_VALUE)
						continue;

					q.add(new int[] { nr, nc });
					distance[nr][nc] = distance[r][c] + 1;
				}

			}

			cntSum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 'W')
						continue;
					cntSum += distance[i][j];

				}
			}

			sb.append("#").append(t).append(" ").append(cntSum).append('\n');
		}

		System.out.println(sb.toString());
	}

}
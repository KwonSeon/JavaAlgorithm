import java.io.*;
import java.util.*;

public class Solution {

	static int n, R, C, map[][], minCnt;
	static final int INF = Integer.MAX_VALUE;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minCnt = Integer.MAX_VALUE;
			simulation(0);

			sb.append("#").append(t).append(" ").append(minCnt == INF ? 0: minCnt).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void simulation(int idx) {

		if (idx == n) {
			minCnt = Math.min(minCnt, cntMap());
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					sb.append(map[i][j]).append(" ");
//				}
//				sb.append('\n');
//			}
//			sb.append('\n');
			return;
		}


		for (int j = 0; j < C; j++) {
			int[][] tmp = copyMatrix(map);
			for (int i = 0; i < R; i++) {
				if (map[i][j] == 0)
					continue;
				brickbreak(i, j);
				gravity();
				simulation(idx + 1);
				map = copyMatrix(tmp);
				break;
			}
		}
	}

	public static void brickbreak(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] current = q.poll();

			int cnt = map[current[0]][current[1]];
			for (int i = 1; i < cnt; i++) {
				for (int dir = 0; dir < 4; dir++) {
					int nr = current[0] + dr[dir] * i;
					int nc = current[1] + dc[dir] * i;

					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;
					if (visited[nr][nc])
						continue;
					if (map[nr][nc] == 0)
						continue;

					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}

			map[current[0]][current[1]] = 0;
		}
	}

	public static void gravity() {
		for (int j = 0; j < C; j++) {
			for (int i = R - 1; i > 0; i--) {
				if (map[i][j] != 0)
					continue;
				for (int k = i - 1; k >= 0; k--) {
					if (map[k][j] == 0)
						continue;
					map[i][j] = map[k][j];
					map[k][j] = 0;
					break;
				}
			}
		}
	}


	public static int[][] copyMatrix(int matrix[][]) {
		int[][] tmp = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmp[i][j] = matrix[i][j];
			}
		}

		return tmp;
	}

	public static int cntMap() {

		int cnt = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0)
					continue;
				cnt++;
			}
		}

		return cnt;
	}
}
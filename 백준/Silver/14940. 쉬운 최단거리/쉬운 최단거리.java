import java.io.*;
import java.util.*;

public class Main {

	static int n, m, matrix[][], start[], map[][];
	static boolean[][] visited;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		matrix = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) start = new int[]{i, j};
				matrix[i][j] = -1;
			}
		}

		bfs();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					sb.append(0).append(" ");
				} else {
					sb.append(matrix[i][j]).append(" ");
				}
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[n][m];
		visited[start[0]][start[1]] = true;
		matrix[start[0]][start[1]] = 0;
		q.offer(start);
		int len = 0;

		while (!q.isEmpty()) {
			len++;
			int size = q.size();
			while (size-- > 0) {
				int[] current = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nr = current[0] + dr[dir];
					int nc = current[1] + dc[dir];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					if (map[nr][nc] == 0) {
						matrix[nr][nc] = 0;
					} else {
						matrix[nr][nc] = len;
						q.offer(new int[]{nr, nc});
					}
				}
			}
		}
	}
}
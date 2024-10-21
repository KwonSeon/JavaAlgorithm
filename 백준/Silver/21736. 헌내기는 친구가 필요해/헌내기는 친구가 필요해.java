import java.io.*;
import java.util.*;

public class Main {

	static int n, m, d[];
	static char[][] map;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		d = new int[2];

		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = line[j];
				if (map[i][j] == 'I') {
					d[0] = i;
					d[1] = j;
				}
			}
		}

		int cnt = bfs();

		System.out.println(cnt == 0 ? "TT" : cnt);
	}

	public static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		q.offer(d);
		visited[d[0]][d[1]] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int[] current = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nr = current[0] + dr[dir];
				int nc = current[1] + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;

				if (visited[nr][nc]) continue;

				if (map[nr][nc] == 'X') continue;

				if (map[nr][nc] == 'P')
					cnt++;

				visited[nr][nc] = true;
				q.offer(new int[]{nr, nc});

			}
		}

		return cnt;
	}
}
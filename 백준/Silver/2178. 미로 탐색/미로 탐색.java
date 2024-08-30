import java.io.*;
import java.util.*;

public class Main {

	static int n, m, maze[][];
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new int[n][m];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				maze[i][j] = line[j] - '0';
			}
		}

		bfs();

	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0 });
		visited = new boolean[n][m];
		visited[0][0] = true;

		int depth = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {

				int[] current = q.poll();
				int r = current[0];
				int c = current[1];

				if (r == n - 1 && c == m - 1) {
					System.out.println(depth);
					return;
				}

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || maze[nr][nc] == 0)
						continue;
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
			depth++;
		}
	}
}
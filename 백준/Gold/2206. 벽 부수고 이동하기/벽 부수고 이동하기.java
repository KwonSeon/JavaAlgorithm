import java.io.*;
import java.util.*;

public class Main {

	static int n, m, map[][];
	static boolean[][][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		bfs();

	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[n][m][2];
		q.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;

		int depth = 1;
		while (!q.isEmpty()) {

			int size = q.size();
			while (size-- > 0) {
				int[] current = q.poll();
				int r = current[0];
				int c = current[1];
				int state = current[2];

				if (r == n - 1 && c == m - 1) {
					System.out.println(depth);
					return;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];

					if (nr < 0 || nr >= n || nc < 0 || nc >= m)
						continue;
					// 벽일 때
					if (map[nr][nc] == 1) {
						// 벽을 이미 한 번 부쉈을 때 통과
						if (state == 1)
							continue;
						// 벽을 안 부쉈을 때
						// 이미 방문했다면 통과
						if (visited[nr][nc][1])
							continue;
						// 벽을 부수면서 방문
						visited[nr][nc][1] = true;
						q.offer(new int[] { nr, nc, 1 });

						// 경로일 때
					} else {
						// 이미 방문했으면 통과
						if (visited[nr][nc][state])
							continue;
						// 방문 처리
						visited[nr][nc][state] = true;
						q.offer(new int[] { nr, nc, state });
					}

				}
			}
			depth++;
		}
		// 경로가 없다면
		System.out.println(-1);
		return;
	}
}
import java.io.*;
import java.util.*;

public class Main {

	static int n, m, map[][];
	static boolean[][][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Node {
		int r;
		int c;
		int distance;
		boolean state;

		public Node(int r, int c, int distance, boolean state) {
			this.r = r;
			this.c = c;
			this.distance = distance;
			this.state = state;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		Queue<Node> q = new ArrayDeque<>();
		visited = new boolean[2][n][m];
		q.offer(new Node(0, 0, 1, false));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {

			Node current = q.poll();

			if (current.r == n - 1 && current.c == m - 1) {
				System.out.println(current.distance);
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = current.r + dr[dir];
				int nc = current.c + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				// 이미 방문했으면 통과
				if (visited[current.state ? 1 : 0][nr][nc])
					continue;
				// 벽일 때
				if (map[nr][nc] == 1) {
					// 벽을 이미 한 번 부쉈을 때 통과
					if (current.state)
						continue;
					// 벽을 안 부쉈을 때
					// 벽을 부수면서 방문
					q.offer(new Node(nr, nc, current.distance + 1, true));

					// 경로일 때
				} else {
					// 방문 처리
					visited[current.state ? 1 : 0][nr][nc] = true;
					q.offer(new Node(nr, nc, current.distance + 1, current.state));
				}

			}

		}
		// 경로가 없다면
		System.out.println(-1);
		return;
	}
}
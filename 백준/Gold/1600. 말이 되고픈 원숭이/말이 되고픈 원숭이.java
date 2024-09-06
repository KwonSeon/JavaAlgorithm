import java.io.*;
import java.util.*;

public class Main {

	static int k, w, h, map[][], minDistance;
	static boolean[][][] visited;

	static int[] dr = { -1, 1, 0, 0, -2, -2, 2, 2, -1, 1, 1, -1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1, -2, 2, -2, 2 };

	static class Monkey {
		int r;
		int c;
		int jump;
		int distance;

		public Monkey(int r, int c, int jump, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.jump = jump;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		System.out.println(minDistance != Integer.MAX_VALUE ? minDistance : -1);
	}

	public static void bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		visited = new boolean[h][w][k + 1];
		q.offer(new Monkey(0, 0, 0, 0));
		visited[0][0][0] = true;
		minDistance = Integer.MAX_VALUE;

		while (!q.isEmpty()) {

			Monkey m = q.poll();

			if (m.r == h - 1 && m.c == w - 1) {
				minDistance = Math.min(minDistance, m.distance);
				continue;
			}

			for (int dir = 0; dir < 12; dir++) {

				int nr = m.r + dr[dir];
				int nc = m.c + dc[dir];

				// 경계 체크
				if (nr < 0 || nr >= h || nc < 0 || nc >= w)
					continue;
				// 벽 여부
				if (map[nr][nc] == 1)
					continue;
				// 점프 안 할 때
				if (dir < 4) {
					// 방문여부
					if (visited[nr][nc][m.jump])
						continue;
					q.offer(new Monkey(nr, nc, m.jump, m.distance + 1));
					visited[nr][nc][m.jump] = true;
					// 점프 할 때
				} else {
					if (m.jump + 1 > k || visited[nr][nc][m.jump + 1])
						continue;
					q.offer(new Monkey(nr, nc, m.jump + 1, m.distance + 1));
					visited[nr][nc][m.jump + 1] = true;
				}

			}

		}

	}
}
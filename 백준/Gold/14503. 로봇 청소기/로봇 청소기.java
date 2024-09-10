import java.io.*;
import java.util.*;

public class Main {

	static int n, m, R, C, D, map[][], cnt;
	static boolean[][] visited;
	static Robot robot;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Robot {
		int r;
		int c;
		int d;
		boolean operating;

		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.operating = true;
		}

		public void rotate() {
			this.d = (this.d + 3) % 4;
		}

		public void forward() {
			this.r = r + dr[d];
			this.c = c + dc[d];
		}

		public void backward() {
			this.r = r - dr[d];
			this.c = c - dc[d];
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		robot = new Robot(R, C, D);

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();

		System.out.println(cnt);

	}

	public static void simulation() {
		visited = new boolean[n][m];

		while (robot.operating) {

			if (!visited[robot.r][robot.c]) {
				visited[robot.r][robot.c] = true;
				cnt++;
			}

			boolean canCleaning = false;

			for (int dir = 0; dir < 4; dir++) {

				int nr = robot.r + dr[dir];
				int nc = robot.c + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;

				if (!visited[nr][nc] && map[nr][nc] == 0) {
					canCleaning = true;
					break;
				}

			}

			if (canCleaning) {
				robot.rotate();
				int nr = robot.r + dr[robot.d];
				int nc = robot.c + dc[robot.d];
				if (!visited[nr][nc] && map[nr][nc] == 0)
					robot.forward();
			} else {
				int nr = robot.r - dr[robot.d];
				int nc = robot.c - dc[robot.d];
				if (map[nr][nc] == 0) {
					robot.backward();
				} else {
					robot.operating = false;
				}
			}

		}
		return;
	}

}
import java.io.*;
import java.util.*;

public class Main {

	static int n, cave[][], minLoss[][], loss;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;

			cave = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dijkstra();

			sb.append("Problem ").append(idx++).append(": ").append(loss).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void dijkstra() {
		final int INF = Integer.MAX_VALUE;
		minLoss = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				minLoss[i][j] = INF;
			}
		}
		visited = new boolean[n][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		pq.offer(new int[] { 0, 0, cave[0][0] });
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int r = current[0];
			int c = current[1];
			int l = current[2];

			if (r == n - 1 && c == n - 1) {
				loss = minLoss[n - 1][n - 1];
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n)
					continue;
				if (visited[nr][nc])
					continue;
				if (minLoss[nr][nc] <= l + cave[nr][nc])
					continue;

				minLoss[nr][nc] = l + cave[nr][nc];
				visited[nr][nc] = true;
				pq.offer(new int[] { nr, nc, minLoss[nr][nc] });
			}

		}
	}
}
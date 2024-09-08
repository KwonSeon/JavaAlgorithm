import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static char[][] map;
	static boolean[][] visited;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][n];

		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = line[j];
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] == '1') {
					pq.offer(bfs(i, j));
				}
			}
		}

		int size = pq.size();
		sb.append(size).append('\n');
		while (size-- > 0) {
			sb.append(pq.poll()).append('\n');
		}
		System.out.println(sb);
	}

	public static int bfs(int startR, int startC) {
		int cnt = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{startR, startC});
		visited[startR][startC] = true;

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0];
			int c = current[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == '0') continue;

				q.offer(new int[]{nr, nc});
				cnt++;
				visited[nr][nc] = true;
			}
		}

		return cnt;
	}
}
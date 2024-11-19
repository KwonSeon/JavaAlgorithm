import java.io.*;
import java.util.*;

public class Main {

	static int n, matrix[][], minRel, cnt;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		matrix = new int[n + 1][n + 1];
		while (true) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (start == -1) break;

			matrix[start][end] = 1;
			matrix[end][start] = 1;
		}

//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				sb.append(matrix[i][j]).append(" ");
//			}
//			sb.append('\n');
//		}

		minRel = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			bfs(i);
			if (matrix[i][0] == minRel) {
				cnt++;
			} else if (matrix[i][0] < minRel) {
				minRel = matrix[i][0];
				cnt = 1;
			}
		}

		sb.append(minRel).append(" ").append(cnt).append('\n');
		for (int i = 1; i <= n; i++) {
			if (matrix[i][0] == minRel)
				sb.append(i).append(" ");
		}

		System.out.println(sb.toString());
	}

	public static void bfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[n + 1];
		visited[idx] = true;
		q.offer(idx);

		int depth = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			while (size-- > 0) {
				int current = q.poll();
				for (int i = 1; i <= n; i++) {
					if (visited[i]) continue;
					if (matrix[current][i] == 0) continue;

					visited[i] = true;
					q.offer(i);
				}
			}
			matrix[idx][0] = depth++;
		}
	}
}
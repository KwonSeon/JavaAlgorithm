import java.io.*;
import java.util.*;

public class Main {

	static int n, m, v, matrix[][];
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		matrix = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			matrix[start][end] = 1;
			matrix[end][start] = 1;
		}

		visited = new boolean[n + 1];
		dfs(v);
		sb.append('\n');
		bfs(v);
		System.out.println(sb.toString());
	}

	public static void dfs(int start) {

		if (visited[start]) return;

		sb.append(start).append(" ");
		visited[start] = true;

		for (int i = 1; i <= n; i++) {
			if (matrix[start][i] == 0)
				continue;
			dfs(i);
		}
	}

	public static void bfs(int start) {
		visited = new boolean[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		sb.append(start).append(" ");

		while (!q.isEmpty()) {
			int current = q.poll();


			for (int i = 1; i <= n; i++) {
				if (visited[i]) continue;

				if (matrix[current][i] == 0)
					continue;

				q.offer(i);
				visited[i] = true;
				sb.append(i).append(" ");
			}
		}

	}
}
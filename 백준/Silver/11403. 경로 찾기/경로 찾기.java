import java.io.*;
import java.util.*;

public class Main {

	static int n, graph[][], matrix[][];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		graph = new int[n][n];
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			bfs(i);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited = new boolean[n];
		q.offer(start);

		while (!q.isEmpty()) {
			int current = q.poll();

			for (int i = 0; i < n; i++) {
				if (graph[current][i] == 0) continue;
				if (visited[i]) continue;
				visited[i] = true;
				matrix[start][i] = 1;
				q.offer(i);
			}
		}

	}
}
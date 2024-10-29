import java.io.*;
import java.util.*;

public class Main {

	static int a, b, n, m, matrix[][];
	static ArrayList<Integer>[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			adj[start].add(end);
			adj[end].add(start);
		}

		bfs();
	}

	public static void bfs() {

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{a, 0});
		visited = new boolean[n + 1];
		visited[a] = true;

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int node = current[0];
			int step = current[1];

			if (node == b) {
				System.out.println(step);
				return;
			}

			for (int neighbor : adj[node]) {
				if (visited[neighbor]) continue;
				visited[neighbor] = true;
				q.offer(new int[]{neighbor, step + 1});
			}
		}

		System.out.println(-1);
	}
}
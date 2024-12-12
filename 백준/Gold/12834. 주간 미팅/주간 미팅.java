import java.io.*;
import java.util.*;

public class Main {

	static int n, v, e, a, b, homes[], ans, map[][];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		homes = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			homes[i] = Integer.parseInt(st.nextToken());
		}

		map = new int[v + 1][v + 1];
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[start][end] = d;
			map[end][start] = d;
		}

		for (int i = 0; i < n; i++) {
			ans += dijkstra(homes[i], a);
			ans += dijkstra(homes[i], b);
		}

		System.out.println(ans);
	}

	public static int dijkstra(int start, int target) {
		visited = new boolean[v + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[]{start, 0});
		visited[start] = true;

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int next = current[0];
			int weight = current[1];

			if (next == target) return weight;

			visited[next] = true;
			for (int i = 1; i <= v; i++) {
				if (visited[i]) continue;
				if (map[next][i] == 0) continue;
				pq.offer(new int[]{i, weight + map[next][i]});
			}
		}

		return -1;
	}
}
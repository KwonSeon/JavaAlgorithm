import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int v, e, k, weights[];
	static final int INF = Integer.MAX_VALUE;

	static ArrayList<ArrayList<int[]>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			list.add(new ArrayList<>());
		}

		weights = new int[v + 1];

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add(new int[]{end, weight});
		}


		for (int i = 1; i <= v; i++) {
			if (i == k) continue;
			weights[i] = INF;
		}

		dijkstra();

		for (int i = 1; i <= v; i++) {
			System.out.println(weights[i] == INF ? "INF" : weights[i]);
		}
	}

	public static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.offer(new int[] {k, 0});

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int start = current[0];
			int weight = current[1];

			if (weight > weights[start]) continue;

			for (int[] neighbor : list.get(start)) {
				int end = neighbor[0];
				int newWeight = weight + neighbor[1];

				if (newWeight < weights[end]) {
					weights[end] = newWeight;
					pq.offer(new int[] {end, newWeight});
				}
			}

		}

	}
}
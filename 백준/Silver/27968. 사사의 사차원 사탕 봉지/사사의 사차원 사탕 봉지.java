import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static long dp[];

	static class Child {
		int idx;
		long demand;

		public Child(int idx, long demand) {
			this.idx = idx;
			this.demand = demand;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		dp = new long[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) {
			long input = Long.parseLong(st.nextToken());
			dp[i] = dp[i - 1] + input;
		}

		PriorityQueue<Child> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.demand, o2.demand));
		PriorityQueue<Child> ans = new PriorityQueue<>((o1, o2) -> o1.idx - o2.idx);

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long input = Long.parseLong(st.nextToken());
			pq.offer(new Child(i, input));
		}

		int idx = 1;
		while (!pq.isEmpty()) {
			Child current = pq.poll();

			if (idx > m) {
				current.demand = idx;
				ans.offer(current);
				continue;
			}

			while (idx <= m && dp[idx] < current.demand) {
				idx++;
			}

			current.demand = idx;
			ans.offer(current);
		}

		while (!ans.isEmpty()) {
			Child current = ans.poll();
			sb.append(current.demand == m + 1 ? "Go away!" : current.demand).append('\n');
		}

		System.out.println(sb.toString());
	}
}
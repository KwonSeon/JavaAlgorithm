import java.io.*;
import java.util.*;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
			if (o1[1] == o2[1]) {
				return o1[0] - o2[0];
			} else {
				return o1[1] - o2[1];
			}
		}));

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			pq.offer(new int[]{x, y});
		}

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			sb.append(current[0]).append(" ").append(current[1]).append('\n');
		}

		System.out.println(sb.toString());
	}
}
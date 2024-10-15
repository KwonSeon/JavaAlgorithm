import java.io.*;
import java.util.*;

public class Main {

	static int n, score, p;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		score = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		// 랭킹이 없는 경우 무조건 1등
		if (n == 0) {
			System.out.println(1);
			return;
		}

		st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
		for (int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}

		int idx = 1;
		int ranking = -1;

		while (!pq.isEmpty()) {
			int current = pq.poll();

			if (score == current) {
				if (ranking == -1)
					ranking = idx;
			} else if (score > current) {
				if (ranking == -1)
					ranking = idx;
				break;
			}

			idx++;
		}

		if (idx > p) {
			ranking = -1;
		} else if (n<p && ranking==-1) {
			ranking= idx;
		}

		System.out.println(ranking);
	}
}
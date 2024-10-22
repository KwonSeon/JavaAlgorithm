import java.io.*;
import java.util.*;

public class Main {

	static int n, l, r, x, diff[], cnt;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		diff = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			diff[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(diff);

		visited = new boolean[n];
		subset(0, 0);

		System.out.println(cnt);
	}

	public static void subset(int idx, int diffSum) {

		if (diffSum > r) return;

		if (idx == n) {
			if (diffSum < l) return;
			if ((findMaxDiff() - findMinDiff()) < x) return;

			cnt++;
			return;
		}

		visited[idx] = true;
		subset(idx + 1, diffSum + diff[idx]);

		visited[idx] = false;
		subset(idx + 1, diffSum);
	}

	public static int findMaxDiff() {
		int temp = 0;

		for (int i = n - 1; i >= 0; i--) {
			if (visited[i]) {
				temp = diff[i];
				break;
			}
		}

		return temp;
	}

	public static int findMinDiff() {
		int temp = 0;

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				temp = diff[i];
				break;
			}
		}

		return temp;
	}

}
import java.io.*;
import java.util.*;

public class Main {

	static int x, minNum, array[], len;
	static final int INF = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		String num = st.nextToken();
		x = Integer.parseInt(num);

		char[] input = num.toCharArray();
		len = input.length;

		array = new int[len];
		for (int i = 0; i < len; i++) {
			array[i] = input[i] - '0';
		}

		visited = new boolean[len];
		minNum = INF;

		permutation(0, 0);

		System.out.println(minNum == INF ? 0 : minNum);
	}

	public static void permutation(int idx, int current) {


		if (idx == len) {
			if (current <= x) return;
			minNum = Math.min(current, minNum);
			return;
		}

		for (int i = 0; i < len; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			permutation(idx + 1, current * 10 + array[i]);
			visited[i] = false;
		}
	}
}
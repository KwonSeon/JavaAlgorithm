import java.io.*;
import java.util.*;

public class Main {

	static int n, m, array[];
	static HashSet<String> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(array);

		permutationWithRepeat(0, array[0], "");

		System.out.println(sb.toString());
	}

	public static void permutationWithRepeat(int cnt, int num, String current) {

		if (cnt == m) {
			if (set.contains(current)) return;
			set.add(current);
			sb.append(current).append('\n');
			return;
		}

		for (int i = 0; i < n; i++) {
			if (num > array[i]) continue;
			permutationWithRepeat(cnt + 1, array[i], current + array[i] + " ");
		}
	}
}
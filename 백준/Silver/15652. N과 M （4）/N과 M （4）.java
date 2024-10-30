import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, array[];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		array = new int[m];

		combination(0);
		System.out.println(sb.toString());
	}

	public static void combination(int idx) {

		if (idx == m) {
			for (int num : array) {
				sb.append(num).append(" ");
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < n; i++) {
			if (idx > 0 && array[idx - 1] > i + 1) continue;
			array[idx] = i + 1;
			combination(idx + 1);
		}
	}
}
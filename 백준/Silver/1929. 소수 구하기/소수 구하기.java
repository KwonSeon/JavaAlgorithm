import java.io.*;
import java.util.*;

public class Main {

	static int m, n;
	static boolean[] decimal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		decimal = new boolean[n + 1];

		for (int i = 2; i <= n; i++) {
			int temp = 1;
			while (i * ++temp <= n) {
				if (decimal[i * temp]) continue;

				decimal[i * temp] = true;
			}
		}

		for (int i = m; i <= n; i++) {
			if (decimal[i] || i==1) continue;
			sb.append(i).append('\n');
		}

		System.out.println(sb.toString());
	}
}
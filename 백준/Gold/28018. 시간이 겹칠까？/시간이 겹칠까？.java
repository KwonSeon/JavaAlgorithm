import java.io.*;
import java.util.*;

public class Main {

	static int n, q, table[], s, e;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		table = new int[1_000_002];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());

			table[s]++;
			table[e + 1]--;
		}

		for (int i = 1; i < table.length; i++) {
			table[i] = table[i] + table[i - 1];
		}

		st = new StringTokenizer(br.readLine());
		q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			sb.append(table[Integer.parseInt(st.nextToken())]).append('\n');
		}

		System.out.println(sb.toString());
	}
}
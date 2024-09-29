import java.io.*;
import java.util.*;

public class Main {

	static int n, m, cnt[];
	static final int addNum = 10_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		cnt = new int[2 * addNum + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());
			cnt[input + addNum]++;
		}

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int input = Integer.parseInt(st.nextToken());
			sb.append(cnt[input + addNum]).append(" ");
		}

		System.out.println(sb.toString());
	}
}
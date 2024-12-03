import java.io.*;
import java.util.*;

public class Main {

	static int n, array[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			array = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}

			int[] stair = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				stair[i] = i;
			}

			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				if (stair[i] == array[i]) continue;

				for (int j = i + 1; j <= n; j++) {
					int temp = stair[i];
					stair[i] = stair[j];
					stair[j] = temp;
					cnt++;
					if (stair[i] == array[i]) break;
				}
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb.toString());
	}
}
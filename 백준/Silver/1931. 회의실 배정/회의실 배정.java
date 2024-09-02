import java.io.*;
import java.util.*;

public class Main {

	static int n, table[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		table = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			table[i][0] = Integer.parseInt(st.nextToken());
			table[i][1] = Integer.parseInt(st.nextToken());
		}

		// 시간 오름차순 정렬
		Arrays.sort(table, (o1, o2) -> {
			if (o1[1] != o2[1]) {
				return o1[1] - o2[1];
			} else {
				return o1[0] - o2[0];
			}
		});

		int cnt = 0;
		int lastEndTime = 0;

		for (int i = 0; i < n; i++) {
			if (table[i][0] >= lastEndTime) {
				lastEndTime = table[i][1];
				cnt++;
			}
		}

		System.out.println(cnt);
	}

}
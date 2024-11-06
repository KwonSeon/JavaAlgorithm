import java.io.*;
import java.util.*;

public class Main {

	static int t, n, stickers[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		t = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			stickers = new int[2][n];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (n>1) {
				stickers[0][1] += stickers[1][0];
				stickers[1][1] += stickers[0][0];
			}
			if (n>2) {
				for (int i = 2; i < n; i++) {
					stickers[0][i] += Math.max(stickers[1][i - 1], Math.max(stickers[0][i - 2], stickers[1][i - 2]));
					stickers[1][i] += Math.max(stickers[0][i - 1], Math.max(stickers[0][i - 2], stickers[1][i - 2]));
				}
			}

			sb.append(Math.max(stickers[0][n - 1], stickers[1][n - 1])).append('\n');
		}

		System.out.println(sb.toString());
	}
}
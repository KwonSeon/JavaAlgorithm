import java.io.*;
import java.util.*;

public class Main {

	static int n, chairs[][];
	static int[] advR = {0, 0, 1, 1};
	static int[] advC = {0, 1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		chairs = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				chairs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = recusion(0, 0, n);
		System.out.println(ans);
	}

	public static int recusion(int r, int c, int len) {

		if (len == 1)
			return chairs[r][c];

		int[] numbers = new int[4];

		int length = len / 2;
		for (int d = 0; d < 4; d++) {
			numbers[d] = recusion(r + advR[d] * length, c + advC[d] * length, length);
		}

		Arrays.sort(numbers);

		return numbers[1];
	}
}
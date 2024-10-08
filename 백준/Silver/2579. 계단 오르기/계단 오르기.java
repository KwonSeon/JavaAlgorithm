import java.io.*;
import java.util.*;

public class Main {

	static int n, stair[], score[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		stair = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}

		if (n == 1) {
			System.out.println(stair[1]);
			return;
		}

		score = new int[n + 1];
		score[1] = stair[1];
		score[2] = stair[1] + stair[2];

		for (int i = 3; i <= n; i++) {
			score[i] = stair[i] + Math.max(stair[i - 1] + score[i - 3], score[i - 2]);
		}

		System.out.println(score[n]);
	}

}
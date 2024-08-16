import java.io.*;
import java.util.*;

public class Main {

	static int s, p, req[], cnt, ACnt, CCnt, GCnt, TCnt, dp[][];
	static String[] dna;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		dna = st.nextToken().split("");
		dp = new int[s + 1][4];
		for (int i = 0; i < s; i++) {
			dp[i + 1] = dp[i].clone();
			if (dna[i].equals("A")) {
				dp[i + 1][0]++;
			} else if (dna[i].equals("C")) {
				dp[i + 1][1]++;
			} else if (dna[i].equals("G")) {
				dp[i + 1][2]++;
			} else if (dna[i].equals("T")) {
				dp[i + 1][3]++;
			}
		}

		st = new StringTokenizer(br.readLine());
		req = new int[4];
		for (int i = 0; i < 4; i++) {
			req[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i <= s - p; i++) {
			if ((dp[i + p][0] - dp[i][0]) < req[0])
				continue;
			if ((dp[i + p][1] - dp[i][1]) < req[1])
				continue;
			if ((dp[i + p][2] - dp[i][2]) < req[2])
				continue;
			if ((dp[i + p][3] - dp[i][3]) < req[3])
				continue;
			cnt++;
		}

		System.out.println(cnt);
	}
}
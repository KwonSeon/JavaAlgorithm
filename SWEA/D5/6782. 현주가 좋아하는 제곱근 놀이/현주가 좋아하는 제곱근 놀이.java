import java.io.*;
import java.util.*;

/**
 * 
 *
 */
public class Solution {

	static long n;
	static int minCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Long.parseLong(st.nextToken());
			minCnt = Integer.MAX_VALUE;
			squareRootGame(n, 0);

			sb.append("#").append(t).append(" ").append(minCnt).append('\n');
		}
		System.out.println(sb.toString());
	}

	public static void squareRootGame(long currentNum, int cnt) {

		if (cnt > minCnt)
			return;

		// 2에 도달하면 리턴
		if (currentNum == 2) {
			minCnt = (int) Math.min(minCnt, cnt);
			return;
		}

		// 제곱근이 존재하면 제곱근
		if (Math.sqrt(currentNum) % 1 == 0) {
			squareRootGame((long) Math.sqrt(currentNum), cnt + 1);
			return;
		}

		// 제곱근이 되지 않으면 제곱근이 되도록 변경
		long sqrt = (long) Math.sqrt(currentNum) + 1;
		long diff = sqrt * sqrt - currentNum;
		squareRootGame(sqrt * sqrt, cnt + (int) diff);

	}
}
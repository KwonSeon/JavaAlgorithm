import java.io.*;
import java.util.*;

public class Main {

	static int n, table[][], maxProfit;

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

		boolean[] isSelected = new boolean[n];
		maxProfit = 0;
		subset(0, 0, 0);


		System.out.println(maxProfit);
	}

	public static void subset(int index, int currentProfit, int preIndex) {

		// 최대 이익 갱신
		if (index >= n) {
			if (index > n) {
				currentProfit -= table[preIndex][1];
			}
			maxProfit = Math.max(maxProfit, currentProfit);
			return;
		}

		// 상담 안 하고 넘김
		subset(index + 1, currentProfit, index);

		// 상담 진행
		subset(index + table[index][0], currentProfit + table[index][1], index);
	}


}
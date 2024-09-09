import java.io.*;
import java.util.*;

public class Main {

	static int n, ability[][], minDiff;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		ability = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		isSelected = new boolean[n];

		minDiff = Integer.MAX_VALUE;

		combination(0, 0);

		System.out.println(minDiff);
	}

	public static void combination(int idx, int start) {

		if (idx == n / 2) {
			int startScore = 0;
			int linkScore = 0;

			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (isSelected[k] && isSelected[j]) {
						startScore += ability[j][k];
					} else if (!isSelected[j] && !isSelected[k]) {
						linkScore += ability[j][k];
					}
				}
			}

			minDiff = Math.min(minDiff, Math.abs(startScore - linkScore));

			return;
		}

		for (int i = start; i < n; i++) {

			isSelected[i] = false;

			combination(idx + 1, i + 1);

			isSelected[i] = true;

		}
	}
}
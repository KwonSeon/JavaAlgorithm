import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			int[][] matrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] nums = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Integer.parseInt(nums[j]);
				}
			}

			// 0 <= i, j <= n-m
			int highScore = 0;
			for (int i = 0; i <= n - m; i++) {
				for (int j = 0; j <= n - m; j++) {

					int killFlies = 0;
					for (int k = 0; k < m; k++) {
						for (int l = 0; l < m; l++) {
							killFlies += matrix[i + k][j + l];
						}
					}
					if (killFlies > highScore) {
						highScore = killFlies;
					}

				}
			}

			// 출력
			sb.append("#").append(t).append(" ").append(highScore).append('\n');
		}

		System.out.println(sb.toString());
	}
}
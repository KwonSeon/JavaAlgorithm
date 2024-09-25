import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

import java.io.*;
import java.util.*;

public class Main {

	static int n, m, minCnt;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new char[n][m];
		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				board[i][j] = line[j];
			}
		}

		minCnt = Integer.MAX_VALUE;

		for (int i = 0; i <= n - 8; i++) {
			for (int j = 0; j <= m - 8; j++) {
				minCnt = Math.min(minCnt, paint(i, j, 'W'));
				minCnt = Math.min(minCnt, paint(i, j, 'B'));
			}
		}

		System.out.println(minCnt);

	}

	public static int paint(int r, int c, char color) {
		int cnt = 0;

		for (int i = r; i < r + 8; i++) {
			if (cnt >= minCnt) break;
			for (int j = c; j < c + 8; j++) {
				if (j % 2 == 0) {
					if (i % 2 == 0) {
						if (board[i][j] != color) cnt++;
					} else {
						if (board[i][j] == color) cnt++;
					}
				} else if (j % 2 == 1) {
					if (i % 2 == 1) {
						if (board[i][j] != color) cnt++;
					} else {
						if (board[i][j] == color) cnt++;
					}
				}
			}
		}

		return cnt;
	}
}
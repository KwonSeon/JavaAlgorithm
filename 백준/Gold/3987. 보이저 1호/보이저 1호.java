import java.io.*;
import java.util.*;

public class Main {

	static int n, m, pr, pc, maxLen, maxDirIdx;
	static final int INF = Integer.MAX_VALUE;
	static char[][] map;
	static char[] directionChar = {'U', 'R', 'D', 'L'};

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = line[j];
			}
		}

		st = new StringTokenizer(br.readLine());
		pr = Integer.parseInt(st.nextToken()) - 1;
		pc = Integer.parseInt(st.nextToken()) - 1;

		for (int dir = 0; dir < 4; dir++) {
			simulation(pr, pc, dir, dir, 0);
			if (maxLen == INF)
				break;
		}

		System.out.println(directionChar[maxDirIdx]);
		System.out.println(maxLen == INF ? "Voyager" : maxLen);
	}

	public static void simulation(int r, int c, int dir, int currentDir, int len) {
		// 무한히 반복되는 경우
		if (pr == r && pc == c && dir == currentDir && len != 0) {
			maxLen = INF;
			maxDirIdx = dir;
			return;
		}

		// 경계를 벗어나거나 블랙홀로 간 경우 종료
		if (r < 0 || r >= n || c < 0 || c >= m || map[r][c] == 'C') {
			if (len <= maxLen) return;
			maxLen = len;
			maxDirIdx = dir;
			return;
		}

		int newCurrentDir = currentDir;
		 if (map[r][c] == '/') {
			if (currentDir % 2 == 0) {
				newCurrentDir += 1;
			} else {
				newCurrentDir -= 1;
			}
		} else if (map[r][c] == '\\') {
			if (currentDir % 2 == 0) {
				newCurrentDir = (currentDir + 3) %4;
			} else {
				newCurrentDir = (currentDir + 1) %4;

			}
		}

		int nr = r + dr[newCurrentDir];
		int nc = c + dc[newCurrentDir];
		simulation(nr, nc, dir, newCurrentDir, len + 1);

	}
}
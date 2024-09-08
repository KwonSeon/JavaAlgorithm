import java.io.*;
import java.util.*;

public class Solution {

	static int n, matrix[][], core[][], coreLength, minLength, currentLength, maxConnection, maxCoreCnt;

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			core = new int[12][2];
			coreLength = 0;
			matrix = new int[n][n];
			maxCoreCnt = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					if (matrix[i][j] == 1 && i > 0 && i < n - 1 && j > 0 && j < n - 1) {
						core[coreLength][0] = i;
						core[coreLength++][1] = j;
						maxCoreCnt++;
					}
				}
			}

			minLength = Integer.MAX_VALUE;
			currentLength = 0;
			maxConnection = 0;
			connectToCalbe(0, 0);

			sb.append("#").append(t).append(" ").append(minLength).append('\n');
		}

		System.out.println(sb);
	}

	public static void connectToCalbe(int idx, int cnt) {

		// 최대 뽑을 수 있는 수가 최대 연결한 수보다 적은 경우 리턴
		if (coreLength - idx + cnt < maxConnection)
			return;

		if (idx == coreLength) {
			if (cnt == 0)
				return;

			// 가장 많이 연결한 경우 길이 갱신
			if (cnt > maxConnection) {
				maxConnection = cnt;
				minLength = currentLength;
				// 같은 수인 경우 최소 길이로 갱신
			} else if (cnt == maxConnection) {
				minLength = Math.min(minLength, currentLength);
			}

			return;
		}

		int r = core[idx][0];
		int c = core[idx][1];

		for (int dir = 0; dir < 5; dir++) {
			if (dir == 0) {
				connectToCalbe(idx + 1, cnt);
				continue;
			}

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			// 전선 연결이 가능하면 전선을 연결하고 카운트
			if (canConnectToCable(nr, nc, dir)) {
				currentLength += toggleCableConnection(nr, nc, dir, 1);
				connectToCalbe(idx + 1, cnt + 1);
				currentLength -= toggleCableConnection(nr, nc, dir, 0);
			} else {
				connectToCalbe(idx + 1, cnt);
			}
		}
	}

	public static boolean canConnectToCable(int r, int c, int direction) {

		if (r < 0 || r >= n || c < 0 || c >= n || matrix[r][c] != 0)
			return false;

		if ((r == 0 || r == n - 1 || c == 0 || c == n - 1) && matrix[r][c] == 0)
			return true;

		if (canConnectToCable(r + dr[direction], c + dc[direction], direction))
			return true;

		return false;
	}

	public static int toggleCableConnection(int r, int c, int direction, int toggle) {

		if (r < 0 || r >= n || c < 0 || c >= n)
			return 0;

		matrix[r][c] = toggle;

		int nr = r + dr[direction];
		int nc = c + dc[direction];

		return toggleCableConnection(nr, nc, direction, toggle) + 1;
	}
}
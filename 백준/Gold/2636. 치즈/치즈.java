import java.io.*;
import java.util.*;

/**
 * 
 * 최외각부터 탐색을 시작해서 치즈를 만나면 지워주고 리턴을 한다. 방문 표시를 했기 때문에 최외각만 지워진다. 처음 치즈의 개수와 지운
 * 치즈를 카운트해서 녹기 전 마지막 남은 치즈의 개수를 파악한다.
 */
public class Main {

	static int R, C, cheeseCnt, board[][], cnt, hour;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 치즈
		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int num = Integer.parseInt(st.nextToken());
				board[i][j] = num;
				// 치즈 조각 수 카운트
				if (num == 1)
					cheeseCnt++;
			}
		}

		// 치즈를 모두 없앨 때까지 반복
		while (cheeseCnt > 0) {
			// 조각 수, 방문 초기화
			cnt = 0;
			visited = new boolean[R][C];
			dfs(0, 0);
			// 조각 지우기
			cheeseCnt -= cnt;
			// 시간 증가
			hour++;
		}

		sb.append(hour).append('\n').append(cnt);
		System.out.println(sb.toString());

	}

	public static void dfs(int r, int c) {

		// 방문한 곳이면 리턴
		if (visited[r][c])
			return;

		// 방문 처리
		visited[r][c] = true;

		// 치즈 녹이기
		if (board[r][c] == 1) {
			board[r][c] = 0;
			// 녹인 치즈 수 카운팅
			cnt++;
			// 탐색 정지
			return;
		}
		
		// 탐색
		 for (int i=0;i<4;i++) {
			 int nr = r + dr[i];
			 int nc = c + dc[i];
			 // 경계 체크
			 if (nr<0||nr>=R||nc<0||nc>=C) continue;
			 
			 dfs(nr,nc);
		 }

	}
}
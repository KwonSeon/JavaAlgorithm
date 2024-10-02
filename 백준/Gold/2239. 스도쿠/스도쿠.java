import java.io.*;
import java.util.*;

/**
 * @author SSAFY
 *
 */
public class Main {

	static int board[][];
	static boolean find;
	static int[] boardR = { 0, 3, 6 };
	static int[] boardC = { 0, 3, 6 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				board[i][j] = line[j] - '0';
			}
		}

		sudoku(0);

		System.out.println(sb.toString());

	}

	public static void sudoku(int idx) {
		if (find)
			return;

		// 마지막에 도달하면 정지
		if (idx == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]);
				}
				sb.append('\n');
			}
			find = true;
			return;
		}

		int r = idx / 9;
		int c = idx % 9;

		// 이미 작성된 경우 통과
		if (board[r][c] != 0) {
			sudoku(idx + 1);

		} else {

			// 스도쿠 그리기 백트래킹
			for (int i = 1; i <= 9; i++) {
				// 숫자를 놓을 수 있는 경우 넘어감
				if (check(r, c, i)) {
					board[r][c] = i;
					sudoku(idx + 1);
				}
				if (find)
					return;
				board[r][c] = 0;
			}
		}
	}

	/**
	 * 가로, 세로의 중복을 체크
	 * 
	 * @param r 시작 좌표
	 * @param c 시작 좌표
	 * @return 중복 여부
	 */
	public static boolean check(int r, int c, int checkNum) {

		// 현재 상태 기록
		for (int i = 0; i < 9; i++) {
			// 보드의 숫자가 0이 아닌 경우 가로 체크
			if (board[r][i] == checkNum) {
				return false;
			}

			// 세로 체크
			if (board[i][c] == checkNum) {
				return false;
			}
		}

		int nr = boardR[r / 3];
		int nc = boardC[c / 3];
		for (int i = nr; i < nr + 3; i++) {
			for (int j = nc; j < nc + 3; j++) {
				if (board[i][j] == checkNum) {
					return false;
				}
			}
		}

		return true;
	}
}
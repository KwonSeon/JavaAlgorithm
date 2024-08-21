import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {

	static int n, m, r, matrix[][], commandCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// commanList
		int[] commanList = new int[r];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			commanList[i] = Integer.parseInt(st.nextToken());
		}
		// 현재 명령어
		int currentCommand = commanList[0];
		commandCnt = 1;
		for (int i = 1; i < r; i++) {
			int temp = commanList[i];
			if (currentCommand == temp) {
				commandCnt++;
			} else {
				command(currentCommand);
				currentCommand = temp;
				commandCnt = 1;
			}
		}
		// 마지막 명령어 실행
		command(currentCommand);

		// 출력
		for (int[] matrixArray : matrix) {
			for (int matrixElement : matrixArray) {
				sb.append(matrixElement).append(" ");
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());

	}

	/**
	 * 명령어 실행
	 * 
	 * @param commandNum 명령어 번호 1. 상하반전 2. 좌우반전 3. 시계방향 90도 4. 반시계방향 90도 5. 시계방향 사분할
	 *                   6. 반시계방향 사분할
	 * @param cnt        실행 횟수
	 */
	public static void command(int commandNum) {
		switch (commandNum) {
		case 1:
			horizonInversion(0);
			break;
		case 2:
			verticalInversion(0);
			break;
		case 3:
			rotate(0, commandNum);
			break;
		case 4:
			rotate(0, commandNum);
			break;
		case 5:
			divideRotate(0, commandNum);
			break;
		case 6:
			divideRotate(0, commandNum);
			break;
		}

	}

	/**
	 * 상하 반전
	 * 
	 * @param cnt 현재 실행 횟수
	 */
	public static void horizonInversion(int cnt) {

		// 최적 실행 횟수 도달 시 종료
		if (cnt == commandCnt % 2)
			return;

		// 상하 반전
		int[][] newMatrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newMatrix[i][j] = matrix[n - 1 - i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = newMatrix[i][j];
			}
		}

		horizonInversion(cnt + 1);
	}

	/**
	 * 좌우 반전
	 * 
	 * @param cnt 현재 실행 횟수
	 */
	public static void verticalInversion(int cnt) {

		// 최적 실행 횟수 도달 시 종료
		if (cnt == commandCnt % 2)
			return;

		// 좌우 반전
		int[][] newMatrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newMatrix[i][j] = matrix[i][m - 1 - j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = newMatrix[i][j];
			}
		}

		verticalInversion(cnt + 1);
	}

	/**
	 * 회전
	 * 
	 * @param cnt 현재 실행 횟수
	 */
	public static void rotate(int cnt, int commandNum) {

		// 회전 방향 설정
		int clockWise = commandNum == 3 ? 1 : 3;

		// 최적 실행 횟수 도달 시 종료
		if (cnt == (commandCnt * clockWise) % 4)
			return;

		// 시계방향 90도 회전
		int[][] newMatrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				newMatrix[i][j] = matrix[n - 1 - j][i];
			}
		}

		matrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = newMatrix[i][j];
			}
		}

		// 배열의 크기 초기
		int temp = n;
		n = m;
		m = temp;

		rotate(cnt + 1, commandNum);
	}

	/**
	 * 사분할 회전
	 * 
	 * @param cnt 현재 실행 횟수
	 */
	public static void divideRotate(int cnt, int commandNum) {

		// 회전 방향 설정
		int clockWise = commandNum == 5 ? 1 : 3;

		// 최적 실행 횟수 도달 시 종료
		if (cnt == (commandCnt * clockWise) % 4)
			return;

		// 시계방향 사분할 회전
		int[][] newMatrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 1사분면인 경우
				if (i < n / 2 && j < m / 2) {
					newMatrix[i][j + m / 2] = matrix[i][j];
					// 2사분면인 경우
				} else if (i < n / 2 && j >= m / 2) {
					newMatrix[i + n / 2][j] = matrix[i][j];
					// 3사분면인 경우
				} else if (i >= n / 2 && j >= m / 2) {
					newMatrix[i][j - m / 2] = matrix[i][j];
					// 4사분면인 경우
				} else {
					newMatrix[i - n / 2][j] = matrix[i][j];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = newMatrix[i][j];
			}
		}

		divideRotate(cnt + 1, commandNum);
	}

}
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tcn = sc.nextInt();
		for (int tc = 1; tc <= tcn; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};

			int[][] matrix = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			int[][] time = new int[n][m];

//		1. 1부터 k까지 반복
			for (int t = 0; t < k; t++) {
//		2. matrix의 세포 위치와 같은 time에 1씩 더해줌
				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < matrix[0].length; j++) {
						if (matrix[i][j] != 0 && time[i][j] < 2 * matrix[i][j]) {
							time[i][j]++;
						}
					}
				}

				// 경계에서 패딩이 필요한지 확인
				boolean needsPadding = false;
				for (int i = 0; i < matrix.length; i++) {
					if (matrix[i][0] != 0 || matrix[i][matrix[0].length - 1] != 0) {
						needsPadding = true;
						break;
					}
				}
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[0][j] != 0 || matrix[matrix.length - 1][j] != 0) {
						needsPadding = true;
						break;
					}
				}

				// 경계에 도달한 경우 패딩 추가
				if (needsPadding) {
					matrix = addPadding(matrix);
					time = addPadding(time);
				}

//		3. matrix를 순회하며 0인 경우 4방향을 확인하여 활성화 된 세포 중 가장 큰 값으로 변경
				for (int i = 0; i < matrix.length; i++) {
					for (int j = 0; j < matrix[0].length; j++) {
						if (matrix[i][j] == 0) {
							for (int l = 0; l < 4; l++) {
//					3-1. 경계가 아닐 것
//					3-2. time의 시간이 활성기간 보다 크며 2배와 같거나 작을 것
								if (i + dr[l] >= 0 && i + dr[l] < matrix.length &&
										j + dc[l] >= 0 && j + dc[l] < matrix[0].length &&
										time[i + dr[l]][j + dc[l]] > matrix[i + dr[l]][j + dc[l]] &&
										time[i + dr[l]][j + dc[l]] <= 2 * matrix[i + dr[l]][j + dc[l]]) {
									if (matrix[i][j] < matrix[i + dr[l]][j + dc[l]]) {
										matrix[i][j] = matrix[i + dr[l]][j + dc[l]];
									}
								}
							}
						}

					}
				}
			}
//		4. matrix를 조회하며 time과 같거나 작은 값들의 수를 체크함
			int cnt = 0;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] != 0 && time[i][j] < 2 * matrix[i][j]) {
						cnt++;
					}
				}
			}

			System.out.printf("#%d %d\n", tc, cnt);
		}
	}

	public static int[][] addPadding(int[][] matrix) {
		int r = matrix.length + 2;
		int c = matrix[0].length + 2;
		int[][] newMatrix = new int[r][c];

		for (int i = 1; i < r - 1; i++) {
			for (int j = 1; j < c - 1; j++) {
				newMatrix[i][j] = matrix[i - 1][j - 1];
			}
		}

		return newMatrix;
	}
}
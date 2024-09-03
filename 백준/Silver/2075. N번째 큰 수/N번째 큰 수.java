import java.io.*;
import java.util.*;

public class Main {

	static int n, matrix[][], rArr[], currentC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. 현재 보고 있는 행을 저장
		rArr = new int[n];
		Arrays.fill(rArr, n - 1);
		int cnt = 0;
		while (cnt++ < n) {
		// 2. 조회하면서 가장 큰 수를 발견하면 그 위치의 열을 저장
			int current = Integer.MIN_VALUE;
			for (int i=0; i<rArr.length;i++){
				int r = rArr[i];
				int c = i;
				if (matrix[r][c] > current){
					currentC = c;
					current = matrix[r][c];
				}
			}
			// 3. 해당 열의 행의 인덱스를 줄임
			rArr[currentC]--;
		}
			// 4. 횟수에 도달하면 출력
		System.out.println(matrix[rArr[currentC]+1][currentC]);
	}
}
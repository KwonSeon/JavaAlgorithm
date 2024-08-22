import java.io.*;
import java.util.*;

public class Solution {

	static int n, maxResult, minResult, operator[], numbers[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			// 사용 가능한 연산자
			operator = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			// 수식에 사용되는 숫자
			numbers = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			// 현재 사용하고 있는 연산자
			int[] usedOperator = new int[4];
			// 값 계산
			maxResult = Integer.MIN_VALUE;
			minResult = Integer.MAX_VALUE;
			
			permutation(1, usedOperator, numbers[0]);
			
			sb.append("#").append(t).append(" ").append(maxResult - minResult).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void permutation(int index, int[] usedOperator, int currentNum) {
		// 연산자 수 넘어가면 종료
		for (int j = 0; j < 4; j++) {
			if (usedOperator[j] > operator[j])
				return;
		}

		// 개수만큼 뽑으면 최대, 최소 갱신
		if (index == n) {
			if (currentNum > maxResult) {
				maxResult = currentNum;
			} 
			if (currentNum < minResult) {
				minResult = currentNum;
			}
			return;
		}

		// 연산하면서 다음 재귀 호출
		for (int i = 0; i < 4; i++) {
			usedOperator[i]++;
			if (i == 0) {
				permutation(index + 1, usedOperator, currentNum + numbers[index]);
			} else if (i == 1) {
				permutation(index + 1, usedOperator, currentNum - numbers[index]);
			} else if (i == 2) {
				permutation(index + 1, usedOperator, currentNum * numbers[index]);
			} else if (i == 3) {
				permutation(index + 1, usedOperator, currentNum / numbers[index]);
			}
			usedOperator[i]--;
		}
	}
}
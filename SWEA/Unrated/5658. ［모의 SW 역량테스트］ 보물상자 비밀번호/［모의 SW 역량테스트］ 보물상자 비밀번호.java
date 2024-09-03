import java.io.*;
import java.util.*;

public class Solution {

	static int n, k, password[];
	static Integer[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			// 한 면의 길이
			int l = n / 4;
			// 입력받기
			password = new int[n];
			char[] line = br.readLine().toCharArray();
			for (int i = 0; i < n; i++) {
				password[i] = hexadecimalToDecimal(line[i]);
			}

			// 내림차순으로 입력받을 list
			list = new Integer[n];
			// 사용 가능한 모든 암호 조합 입력받기
			int start = 0;
			int listIdx = 0;
			while (start < l) {

				int idx = start;
				while (idx < n + start) {

					int current = 0;
					for (int i = idx; i < idx + l; i++) {
						current = current * 16 + password[i % n];
					}

					list[listIdx++] = current;

					idx += l;
				}

				start++;
			}
			// 암호 조합 내림차순 정렬
			Arrays.sort(list, (o1, o2) -> o2 - o1);

			// 출력
			int cnt = 0;
			int idx = 0;
			int prev = 0;
			int current = 0;
			while (cnt < k) {
				current = list[idx++];
				if (prev != current) {
					prev = current;
					cnt++;
				}
			}

			sb.append("#").append(t).append(" ").append(current).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * @param character 입력받은 문자
	 * @return 16진수를 10진수로 변환
	 */
	public static int hexadecimalToDecimal(int character) {
		if (character >= 'A') {
			return character - 'A' + 10;
		}
		return character - '0';
	}
}
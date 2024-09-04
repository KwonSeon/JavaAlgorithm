import java.io.*;

public class Main {

	static int MCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 1. k를 기준으로 분리
		char[] numbers = br.readLine().toCharArray();
		int len = numbers.length;

		// 2. 최대 출력
		for (int i = 0; i < len; i++) {
			// k 이면
			if (numbers[i] == 'K') {
				sb.append(5);
				// M의 수만큼 0
				while (MCnt-- > 0) {
					sb.append(0);
				}
				MCnt = 0;
			} else {
				// m이면
				MCnt++;
			}
		}
		// 마지막이 k가 아니면 MCnt 처리
		if (numbers[len - 1] != 'K') {
			while (MCnt-- > 0) {
				sb.append(1);
			}
			MCnt = 0;
		}

		sb.append('\n');

		// 3. 최소 출력
		for (int i = 0; i < len; i++) {
			// k이면
			if (numbers[i] == 'K') {
				if (MCnt > 0)
					sb.append(1);
				while (--MCnt > 0) {
					sb.append(0);
				}
				sb.append(5);
				MCnt = 0;
			} else {
				MCnt++;
			}
		}
		// 마지막이 k가 아니면 MCnt 처리
		if (numbers[len - 1] != 'K') {
			sb.append(1);
			while (--MCnt > 0) {
				sb.append(0);
			}
		}

		System.out.println(sb);
	}
}
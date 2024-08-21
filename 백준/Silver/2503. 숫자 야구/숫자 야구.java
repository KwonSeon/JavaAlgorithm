import java.io.*;
import java.util.*;

public class Main {

	static boolean[] isSelect;
	static int n, cnt, balls[][], score[][], board[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		balls = new int[n][3];
		score = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			balls[i][0] = line.charAt(0) - '0';
			balls[i][1] = line.charAt(1) - '0';
			balls[i][2] = line.charAt(2) - '0';
			score[i][0] = Integer.parseInt(st.nextToken());
			score[i][1] = Integer.parseInt(st.nextToken());
		}

		cnt = 0;
		isSelect = new boolean[10];
		board = new int[3];
		permutation(0);

		System.out.println(cnt);
	}

	public static void permutation(int index) {

		if (index == 3) {
			for (int j = 0; j < n; j++) {
				int strike = 0;
				int ball = 0;
				if (balls[j][0] == board[0]) strike++;
				if (balls[j][1] == board[1]) strike++;
				if (balls[j][2] == board[2]) strike++;

				if (balls[j][0] == board[1] || balls[j][0] == board[2]) ball++;
				if (balls[j][1] == board[0] || balls[j][1] == board[2]) ball++;
				if (balls[j][2] == board[0] || balls[j][2] == board[1]) ball++;

				if (strike != score[j][0] || ball != score[j][1]) return;
			}

			cnt++;

			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (isSelect[i]) continue;
			isSelect[i] = true;
			board[index] = i;
			permutation(index + 1);
			isSelect[i] = false;
		}
	}
}
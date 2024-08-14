import java.io.*;
import java.util.*;

/**
 * 
 * 1. 조합으로 3장의 카드 배열 만들기 2. m을 넘지 않는 가장 큰 값 저장
 */
public class Main {

	static int highScore = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);

		// 1. 카드 배열, temp 생성
		int[] cards = new int[n];
		line = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(line[i]);
		}
		int[] temp = new int[3];

		// 2. 조합 실행, n, m, cards, temp, index, start
		blackJack(n, m, cards, temp, 0, 0);

		System.out.println(highScore);
	}

	/**
	 * blackJack 실행, 3장의 카드를 뽑아서
	 * 
	 * @param n     카드 범위, 실행 횟수
	 * @param m     최대 카드 합
	 * @param cards 카드 배열
	 * @param temp  뽑은 카드를 저장
	 * @param index depth, 뽑은 카드 수
	 * @param start 반복문 시작 시점
	 */
	public static void blackJack(int n, int m, int[] cards, int[] temp, int index, int start) {

		if (index == 3) {
			int score = 0;
			for (int card : temp) {
				score += card;
			}
			if (score <= m && score > highScore) {
				highScore = score;
			}
			return;
		}

		for (int i = start; i < n; i++) {
			temp[index] = cards[i];
			blackJack(n, m, cards, temp, index + 1, i + 1);
		}
	}
}
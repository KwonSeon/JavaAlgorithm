import java.io.*;
import java.util.*;

public class Main {

	static int n, minPrice, alphabet[], bookPrice[];
	static String t, bookTitle[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		t = st.nextToken();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		bookPrice = new int[n];
		bookTitle = new String[n];

		// 책 입력받기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			bookPrice[i] = Integer.parseInt(st.nextToken());
			bookTitle[i] = st.nextToken();
		}

		// 필요한 알파벳 개수 찾기
		alphabet = new int['Z'-'A' +1];
		for (int i = 0; i < t.length(); i++) {
			alphabet[t.charAt(i) - 'A']++;
		}

		boolean[] isSelected = new boolean[n];
		minPrice = Integer.MAX_VALUE;
		subset(0, 0, isSelected);

		System.out.println(minPrice < Integer.MAX_VALUE ? minPrice : -1);
	}

	public static void subset(int index, int currentPrice, boolean[] isSelected) {

		if (currentPrice > minPrice) return;

		if (index == n) {
			int[] currentAlphabet = new int['Z'-'A' + 1];
			for (int i = 0; i < n; i++) {
				if (!isSelected[i]) continue;
				String title = bookTitle[i];
				for (int j = 0; j < title.length(); j++) {
					currentAlphabet[title.charAt(j)-'A']++;
				}
			}

			for (int i = 0; i < alphabet.length; i++) {
				if (currentAlphabet[i] < alphabet[i]) return;
			}

			minPrice = Math.min(minPrice, currentPrice);
			return;
		}

		isSelected[index] = false;
		subset(index + 1, currentPrice, isSelected);
		isSelected[index] = true;
		subset(index + 1, currentPrice + bookPrice[index], isSelected);
	}
}
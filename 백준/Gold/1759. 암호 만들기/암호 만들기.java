import java.io.*;
import java.util.*;

public class Main {

	static int l, c;
	static char[] alphabet;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		String[] line = br.readLine().split(" ");
		// 알파벳 입력 받기
		boolean[] alphabets = new boolean['z' + 1];
		for (int i = 0; i < c; i++) {
			alphabets[line[i].charAt(0)] = true;
		}

		// 알파벳 정렬 boolean 사용
		alphabet = new char[c];
		int idx = 0;
		for (int i = 'a'; i <= 'z'; i++) {
			if (alphabets[i])
				alphabet[idx++] = (char) i;

		}

		char[] password = new char[l];
		combination(0, 0, password, 0, 0);

		System.out.println(sb.toString());
	}

	public static void combination(int index, int start, char[] password, int consonant, int vowel) {

		if (index == l) {
			if (consonant < 2 || vowel < 1)
				return;
			sb.append(password).append('\n');
			return;
		}

		for (int i = start; i < c; i++) {

			// 자음, 모음 개수
			int isConsonant = 0;
			int isVowel = 0;
			if (isVowel(alphabet[i])) {
				isVowel++;
			} else {
				isConsonant++;
			}
			// 사용할 암호 저장
			password[index] = alphabet[i];
			combination(index + 1, i + 1, password, consonant + isConsonant, vowel + isVowel);
		}

	}

	/**
	 * @param alph 알파벳
	 * @return 모음이면 true, 자음이면 false
	 */
	public static boolean isVowel(char alph) {

		if (alph == 'a' || alph == 'e' || alph == 'i' || alph == 'o' || alph == 'u') {
			return true;
		}
		return false;
	}
}
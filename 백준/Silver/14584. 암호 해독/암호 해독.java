import java.io.*;
import java.util.*;

public class Main {

	static int n, offset;
	static char line[], dictionary[][];

	static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());

		line = br.readLine().toCharArray();

		n = Integer.parseInt(br.readLine());

		dictionary = new char[n][];
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			dictionary[i] = input;
		}


		// 원문
		for (int i = 0; i < line.length; i++) {
			// 사전
			for (char[] dict : dictionary) {
				int len = dict.length;
				if (line.length - i < len) continue;
				int tempOffset = findOffset(line[i], dict[0]);
				boolean flag = true;

				for (int j = 0; j < len; j++) {
					if (decode(line[i + j], tempOffset) != dict[j]) {
						flag = false;
						break;
					}
				}

				if (flag) {
					find = true;
					offset = tempOffset;
				}
			}

			if (find) break;
		}


		for (char c : line) {
			sb.append(decode(c, offset));
		}

		System.out.println(sb.toString());

	}

	public static char decode(char input, int offset) {
		if (input + offset <= 'z') {
			return (char) (input + offset);
		} else {
			return (char) (input - 26 + offset);
		}
	}

	public static int findOffset(char orginChar, char decoded) {
		int temp = decoded - orginChar;

		if (temp < 0) temp += 26;

		return temp;
	}

}
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		1. 문장을 입력 받는다
		String sentence = sc.nextLine();
//		System.out.println(sentence.length());

		int a, b, i, len, initial, answer;
		len = sentence.length();

//		2. 문장을 하나씩 보면서 변화를 체크한다.
//		2-1. a=1, b=0, inital number를 기록해둔다..
		a = 1;
		b = 0;
		initial = sentence.charAt(0) - '0';

		for (i = 0; i < len - 1; i++) {
			int current = sentence.charAt(i) - '0';
			int check = sentence.charAt(i + 1) - '0';

//		2-2. 현재 문자와 다음 문자가 같으면 넘어간다.
			if (current == check) {
				continue;
			} else {
//		2-3. 현재 문자와 다음 문자가 달라질 때, 처음 수와 같으면 a++, 다르면 b++
				if (check == initial) {
					a++;
				} else {
					b++;
				}
			}
		}
//		3. a, b 중 작은 수 출력
		answer = Math.min(a, b);
		System.out.println(answer);
	}
}

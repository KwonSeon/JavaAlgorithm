import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Double> stack = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		double[] values = new double[n];

		// 1. 표기식 입력
		String postfix = br.readLine();

		// 2. double[n]으로 값들 입력
		for (int i = 0; i < n; i++) {
			values[i] = Double.parseDouble(br.readLine());

		}

		// 3. Character - 'A'로 비교
		int len = postfix.length();

		for (int i = 0; i < len; i++) {
			char character = postfix.charAt(i);
			int index = character - 'A';

			// 3-1. 0 이상 26 미만이면 index로 사용하여 int[index]의 해당 값을 가져오고 stack에 저장
			if (index >= 0 && index < 26) {
				stack.add(values[index]);
			}
			// 3-2. 위에 해당하지 않으면 stack에서 숫자를 꺼내와 계산 후 stack에 저장
			else {
				double afterNum = stack.pop();
				double beforeNum = stack.pop();
				double result = 0;

				switch (character){
					case '+':
						result = beforeNum + afterNum;
						break;
					case '-':
						result = beforeNum - afterNum;
						break;
					case '*':
						result = beforeNum * afterNum;
						break;
					case '/':
						result = beforeNum / afterNum;
						break;
				}
				stack.add(result);
			}
		}

		System.out.printf("%.2f", stack.pop() );
	}
}

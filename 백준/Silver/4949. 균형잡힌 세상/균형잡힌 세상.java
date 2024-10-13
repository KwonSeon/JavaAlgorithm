import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String line = br.readLine();
			if (line.equals(".")) 
				break;
			
			Stack<Character> stack = new Stack<>();
			boolean isBalanced = true;

			for (char ch : line.toCharArray()) {
				if (ch == '(' || ch == '[') {
					stack.push(ch);
				} else if (ch == ')') {
					if (!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
					} else {
						isBalanced = false;
						break;
					}
				} else if (ch == ']') {
					if (!stack.isEmpty() && stack.peek() == '[') {
						stack.pop();
					} else {
						isBalanced = false;
						break;
					}
				}
			}
			
			// 스택이 비어있고 균형이 맞았는지 여부 확인
			if (isBalanced && stack.isEmpty()) {
				sb.append("yes").append('\n');
			} else {
				sb.append("no").append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}
}
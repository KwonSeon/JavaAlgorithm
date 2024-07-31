import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int size = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[10000];

		for (int i = 0; i < n; i++) {
			stack(arr, br.readLine());
		}
	}

	public static void stack(int[] arr, String order) {
		String[] orders = order.split(" ");


		switch (orders[0]) {
			case "push":
				arr[size++] = Integer.parseInt(orders[1]);
				break;
			case "pop":
				if (size == 0) {
					System.out.println(-1);
				} else {
					System.out.println(arr[size - 1]);
					arr[size-1] = 0;
					size--;
				}
				break;
			case "size":
				System.out.println(size);
				break;
			case "empty":
				if (size == 0) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "top":
				if (size == 0) {
					System.out.println(-1);
				} else {
					System.out.println(arr[size - 1]);
				}
				break;
		}
	}
}

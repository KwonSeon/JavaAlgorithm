import java.io.*;
import java.util.*;

/**
 * 
 *
 */
public class Main {

	static StringBuilder sb = new StringBuilder();
	static int n, image[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		image = new int[n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				if (line.charAt(j) == '0')
					continue;
				image[i][j]++;
			}
		}

		divideAndConquer(0, 0, n);
		

		System.out.println(sb.toString());
	}

	public static void divideAndConquer(int r, int c, int size) {

		// 같은 경우
		int compressedNum = compress(r, c, size);
		if (compressedNum != -1) {
			sb.append(compressedNum);
			return;
		}

		// 같지 않은 경우
		sb.append("(");
		divideAndConquer(r, c, size / 2);
		divideAndConquer(r, c + size / 2, size / 2);
		divideAndConquer(r + size / 2, c, size / 2);
		divideAndConquer(r + size / 2, c + size / 2, size / 2);
		sb.append(")");
	}

	public static int compress(int r, int c, int size) {

		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				// 일치하지 않으면 -1 리턴
				if (image[i][j] != image[r][c])
					return -1;
			}
		}

		// 일치하면 압축
		return image[r][c];
	}
}
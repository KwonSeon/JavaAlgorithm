import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, people[], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			make();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

					union(a, b);
			}

			cnt = 0;
			for (int i = 1; i <= n; i++) {
                if (findSet(i) == i) {
                    cnt++;
                }
            }

			sb.append("#").append(t).append(" ").append(cnt).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void make() {
		people = new int[n + 1];
		for (int i = 1; i <= n; i++) {
            people[i] = i;
        }
	}

	public static int findSet(int index) {
		if (people[index] == index) {
			return people[index];
		}
		return people[index] = findSet(people[index]);
	}

	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot != bRoot) {
            people[bRoot] = aRoot;  // 단순히 bRoot를 aRoot로 병합
        }
	}

}
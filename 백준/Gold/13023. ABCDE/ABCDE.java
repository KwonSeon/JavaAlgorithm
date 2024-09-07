import java.io.*;
import java.util.*;

public class Main {

	static int n, m, set[];
	static boolean[] isSelected, root;
	static ArrayList<ArrayList<Integer>> adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<Integer>());
		}

		make();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int input1 = Integer.parseInt(st.nextToken());
			int input2 = Integer.parseInt(st.nextToken());
			adjList.get(input1).add(input2);
			adjList.get(input2).add(input1);
			union(input1, input2);
		}

		root = new boolean[n];
		isSelected = new boolean[n];

//		for (int idx : set) {
//			if (root[idx])
//				continue;
//			root[idx] = true;
//			if (checkConnection(1, idx))
//				return;
//		}
		for (int i = 0; i < n; i++) {
			if (checkConnection(0, i))
				return;
		}

		System.out.println(0);

	}

	public static boolean checkConnection(int cnt, int index) {

		if (cnt == 5) {
			System.out.println(1);
			return true;
		}

		for (int idx : adjList.get(index)) {
			if (isSelected[idx])
				continue;
			isSelected[idx] = true;
			if (checkConnection(cnt + 1, idx))
				return true;
			isSelected[idx] = false;
		}

		return false;
	}

	public static void make() {
		set = new int[n];
		for (int i = 0; i < n; i++) {
			set[i] = i;
		}
	}

	public static int findset(int a) {
		if (a == set[a])
			return a;
		return findset(set[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findset(a);
		int bRoot = findset(b);

		if (aRoot == bRoot)
			return false;
		if (a <= b) {
			set[b] = aRoot;
		} else {
			set[a] = bRoot;
		}
		return true;
	}
}
import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k;
	static long dmgList[], bossList[][], mesos, mesoList[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dmgList = new long[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			dmgList[i] = Long.parseLong(st.nextToken());
		}

		bossList = new long[k][2];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			bossList[i][0] = Long.parseLong(st.nextToken());
			bossList[i][1] = Long.parseLong(st.nextToken());
		}

		mesoList = new long[n];
		for (int i = 0; i < n; i++) {
			mesos = 0;
			subset(0, 15 * 60, 0, dmgList[i]);
			mesoList[i] = mesos;
		}

		Arrays.sort(mesoList);

		long ans = 0;
		for (int i = 1; i <= m; i++) {
			ans += mesoList[n - i];
		}

		System.out.println(ans);
	}

	public static void subset(int idx, long remainingTime, long currentMesos, long dmg) {

		if (idx == k) {
			mesos = Math.max(mesos, currentMesos);
			return;
		}

		long requiredTime = bossList[idx][0] / dmg;
		if (bossList[idx][0] % dmg > 0) requiredTime++;

		if (remainingTime - requiredTime >= 0) {
			subset(idx + 1, remainingTime - requiredTime, currentMesos + bossList[idx][1], dmg);
		}
		subset(idx + 1, remainingTime, currentMesos, dmg);
	}
}
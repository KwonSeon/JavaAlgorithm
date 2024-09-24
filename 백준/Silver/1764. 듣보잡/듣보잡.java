import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static HashSet<String> unheardNames;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		unheardNames = new HashSet<>();
		for (int i = 0; i < n; i++) {
			unheardNames.add(br.readLine());
		}

		List<String> unseenNames = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			String unseenName = br.readLine();

			if (unheardNames.contains(unseenName)) {
				unseenNames.add(unseenName);
			}
		}
		
		unseenNames.sort((o1, o2) -> o1.compareTo(o2));

		System.out.println(unseenNames.size());
		for (String unseenName:unseenNames){
			System.out.println(unseenName);
		}
	}


}
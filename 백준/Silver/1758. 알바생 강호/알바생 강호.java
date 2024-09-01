import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int n;
    static Integer[] tip;
    static long tips;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tip = new Integer[n];
        for (int i = 0; i < n; i++) {
            tip[i] = Integer.parseInt(br.readLine());
        }

        // 내림차순 정렬
        Arrays.sort(tip, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            long result = tip[i] - i;
            if (result > 0) {
                tips += result;
            }
        }

        System.out.println(tips);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static boolean check = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        // 1. 문자열 체크
        for (int i = 0; i < n; i++) {
            check = true;
            String str = br.readLine();

            // 2. 빈 배열 만들기
            ArrayList<Character> checkList = new ArrayList<>();

            // 3. 처음 문자 빈 배열에 넣기
            checkList.add(str.charAt(0));

            // 4. 다음 문자와 현재 문제 비교
            for (int j = 0; j < str.length() - 1; j++) {
                // 5. 다음 문자가 현재 문자와 같은 경우 continue
                if (str.charAt(j) == str.charAt(j + 1)) {
                    continue;
                    // 6. 다음 문자가 현재 문자와 다른 경우
                    // 6-1. 배열에 다음 문자가 존재하면 멈춤
                } else if (checkList.contains(str.charAt(j + 1))) {
                    check = false;
                    break;
                } else {
                    // 6-2. 배열에 다음 문자가 존재하지 않는 경우
                    //      배열에 문자를 추가
                    checkList.add(str.charAt(j + 1));
                }
            }
            // 7. 문자열이 끝나면 cnt++
            if (check) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
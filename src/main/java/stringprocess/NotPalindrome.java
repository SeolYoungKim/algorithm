package stringprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://www.acmicpc.net/problem/15927">15927</a>
 */
public class NotPalindrome {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String target = br.readLine();
            int targetLength = target.length();

            // 회문이 아니면 길이 반환
            if (isNotPalindrome(target)) {
                System.out.println(targetLength);
                return;
            }

            // 하나의 문자로만 이뤄진 경우 회문이 아닌 부분 문자열은 없음
            if (containsOnlyOneChar(target)) {
                System.out.println(-1);
                return;
            }

            // 회문구조가 하나의 문자로만 이뤄지지 않은 경우, 한 개의 문자만 제외하면 회문이 아니게 됨
            System.out.println(targetLength - 1);
        }
    }

    private static boolean isNotPalindrome(String target) {
        int leftIndex = 0;
        int rightIndex = target.length() - 1;

        while (leftIndex < rightIndex) {
            if (target.charAt(leftIndex) != target.charAt(rightIndex)) {
                return true;
            }
            leftIndex++;
            rightIndex--;
        }

        return false;
    }

    private static boolean containsOnlyOneChar(String target) {
        int length = target.length();
        char firstChar = target.charAt(0);
        for (int i = 1; i < length; i++) {
            if (firstChar != target.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

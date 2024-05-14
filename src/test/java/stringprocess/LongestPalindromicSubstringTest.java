package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/description/">5.LongestPalindromicSubstring</a>
 */
public class LongestPalindromicSubstringTest {
    /*
     * 투 포인터가 팰린드롬을 발견하면 확장하는 형태로 풀이
     * - 팰린드롬 판별만 하면 됨 -> 점점 확장 해나가면서 가장 긴 팰린드롬 판별
     * - 팰린드롬은 짝수 or 홀수 형태 (ex: bbbb or abcba) -> 따라서 짝수 또는 홀수를 판별해야 함
     * - 2칸, 3칸으로 구성된 투 포인터
     *   - 2칸 : 2 -> 4 -> 6 ... (2칸은 양측 2개가 같으면 팰린드롬 -> 확장하면서 양측이 같은지 확인)
     *   - 3칸 : 3 -> 5 -> 7 ... (3칸 또한 양측 2개만 같으면 팰린드롬 -> 확장하면서 양측이 같은지 확인)
     */
    private int leftIdxForMaxLength;
    private int maxLength;

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) {
            return s;
        }

        for (int leftIdx = 0; leftIdx < length - 1; leftIdx++) {
            extendPalindrome(s, leftIdx, leftIdx + 1);
            extendPalindrome(s, leftIdx, leftIdx + 2);
        }

        return s.substring(leftIdxForMaxLength, leftIdxForMaxLength + maxLength);
    }

    private void extendPalindrome(String s, int leftIdx, int rightIdx) {
        // 투 포인터가 유효 범위 내에 있고, 양쪽 끝 문자가 일치하는 경우 범위를 확장
        int length = s.length();
        while ((0 <= leftIdx && rightIdx < length) && (s.charAt(leftIdx) == s.charAt(rightIdx))) {
            leftIdx--;
            rightIdx++;
        }

        // 범위 확장이 끝나면 길이 체크 (각 인덱스는 회문이 아닌 경우를 가리키고 있어서 아래와 같이 길이를 계산함)
        int palindromeLength = rightIdx - leftIdx - 1;
        if (maxLength < palindromeLength) {
            leftIdxForMaxLength = leftIdx + 1; // leftIdx가 회문이 아닌 경우를 가리키고 있고, 1개 뒤가 회문인 경우를 가리키고 있음
            maxLength = palindromeLength;
        }
    }

    public static boolean isPalindrome(String s) {
        int length = s.length();
        int leftIdx = 0;
        int rightIdx = length - 1;

        while (leftIdx < rightIdx) {
            if (s.charAt(leftIdx) != s.charAt(rightIdx)) {
                return false;
            }

            leftIdx++;
            rightIdx--;
        }

        return true;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "babad,bab",
            "cbbd,bb"
    })
    void test(String input, String output) {
        assertThat(longestPalindrome(input)).isEqualTo(output);
    }

    @DisplayName("회문 구조 판별로직")
    @Test
    void isPalindromeTest() {
        assertThat(isPalindrome("abba")).isTrue();
        assertThat(isPalindrome("abca")).isFalse();
    }
}

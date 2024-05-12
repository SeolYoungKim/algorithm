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
    public static String longestPalindrome(String s) {
        if (isPalindrome(s)) {
            return s;
        }

        int length = s.length();
        String result = "";

        for (int leftIdx = 0; leftIdx < length - 1; leftIdx++) {
            int rightIdx = length;
            while (leftIdx < rightIdx) {
                String substring = s.substring(leftIdx, rightIdx);
                if (isPalindrome(substring)) {
                    if (result.length() < substring.length()) {
                        result = substring;
                    }
                }
                rightIdx--;
            }
        }

        return result;
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

package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidPalindromeTest {
    public static boolean isPalindrome(String s) {
        String lowerCase = s.toLowerCase();
        String gapRemoved = lowerCase.replaceAll(" ", "");
        String onlyAlphabetOrNumber = gapRemoved.replaceAll("[^a-z0-9]", "");

        int length = onlyAlphabetOrNumber.length();
        int midIdx = length / 2;
        int lastIdx = length - 1;

        for (int i = 0; i < midIdx; i++) {
            char left = onlyAlphabetOrNumber.charAt(i);
            char right = onlyAlphabetOrNumber.charAt(lastIdx - i);
            if (left != right) {
                return false;
            }
        }

        return true;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A man, a plan, a canal: Panama=true",
            "race a car=false",
            "' '=true",
            "0P=false",
            "a=true"
    }, delimiter = '=')
    void test(String s, boolean expected) {
        assertThat(isPalindrome(s)).isEqualTo(expected);
    }
}

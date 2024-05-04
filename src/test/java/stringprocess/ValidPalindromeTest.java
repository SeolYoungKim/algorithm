package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidPalindromeTest {
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }

        int leftIdx = 0;
        int rightIdx = s.length() - 1;

        while (leftIdx < rightIdx) {
            char left = s.charAt(leftIdx);
            char right = s.charAt(rightIdx);

            if (!Character.isLetterOrDigit(left)) {
                leftIdx++;
            } else if (!Character.isLetterOrDigit(right)) {
                rightIdx--;
            } else {
                if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                    return false;
                }
                leftIdx++;
                rightIdx--;
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

package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidPalindromeTest {
    public static boolean isPalindrome(String s) {
        String validString = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(validString).reverse().toString();

        return validString.equals(reversed);
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

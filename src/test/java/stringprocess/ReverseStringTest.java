package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReverseStringTest {
    public static char[] reverseString(char[] s) {
        int midIdx = s.length / 2;
        int lastIdx = s.length - 1;

        for (int i = 0; i < midIdx; i++) {
            char left = s[i];
            char right = s[lastIdx - i];

            s[i] = right;
            s[lastIdx - i] = left;
        }

        return s;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "hello=olleh",
            "Hannah=hannaH"
    }, delimiter = '=')
    void test(String input, String output) {
        char[] s = input.toCharArray();
        assertThat(reverseString(s)).isEqualTo(output.toCharArray());
    }
}

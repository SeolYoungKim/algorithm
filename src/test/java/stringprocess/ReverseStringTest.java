package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReverseStringTest {
    public char[] reverseString(char[] s) {
        int leftIdx = 0;
        int rightIdx = s.length - 1;

        while (leftIdx < rightIdx) {
            char temp = s[leftIdx];
            s[leftIdx] = s[rightIdx];
            s[rightIdx] = temp;

            leftIdx++;
            rightIdx--;
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

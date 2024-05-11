package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/most-common-word/description/">819.MostCommonWord</a>
 */
public class MostCommonWordTest {
    public static String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.split("\\W+");

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            String lowerCase = word.toLowerCase();
            map.put(lowerCase, map.getOrDefault(lowerCase, 0) + 1);
        }

        Set<String> bannedSet = new HashSet<>(List.of(banned));
        return map.entrySet()
                .stream()
                .filter(entry -> !bannedSet.contains(entry.getKey()))
                .max(Entry.comparingByValue())
                .map(Entry::getKey)
                .get();
    }

    public static Stream<Arguments> provideParagraphAndBanned() {
        return Stream.of(
                Arguments.of("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}, "ball"),
                Arguments.of("a.", new String[]{}, "a")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParagraphAndBanned")
    void test(String paragraph, String[] banned, String result) {
        assertThat(mostCommonWord(paragraph, banned)).isEqualTo(result);
    }

    @DisplayName("공백(' ') 또는 !?',;. 로 문자를 구분하고 배열을 생성한다")
    @Test
    void test() {
        String str = "a b! c? 'd' e,f; g.";
//        String[] split = str.split("[\\s!?',;.]+");
        String[] split = str.split("\\W+");
        assertThat(split).containsExactly("a", "b", "c", "d", "e", "f", "g");
    }
}

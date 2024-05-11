package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/group-anagrams/description/">49.GroupAnagrams</a>
 */
public class GroupAnagramTest {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {  // 최대 길이 10,000
            char[] charArray = str.toCharArray();  // 최대 길이 100
            Arrays.sort(charArray);  // O(NlogN) : 100 * 10 = 1,000
            String key = String.valueOf(charArray);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }  // O(N^2 * logN) : 10,000 * 1,000 = 최악 10,000,000

        return map.values()
                .stream()
                .toList();
    }

    @ParameterizedTest
    @MethodSource("provideWords")
    void test(String[] input, List<List<String>> expected) {
        List<List<String>> actual = groupAnagrams(input);
        actual.forEach(list -> list.sort(Comparator.naturalOrder()));
        expected.forEach(list -> list.sort(Comparator.naturalOrder()));
        assertThat(actual).usingElementComparator(Comparator.comparing(List::toString)).containsExactlyInAnyOrderElementsOf(expected);
    }

    public static Stream<Arguments> provideWords() {
        return Stream.of(
                Arguments.of(
                        new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                        Arrays.asList(Arrays.asList("bat"), Arrays.asList("nat", "tan"), Arrays.asList("ate", "eat", "tea"))
                ),
                Arguments.of(
                        new String[]{""},
                        Arrays.asList(Arrays.asList(""))
                ),
                Arguments.of(
                        new String[]{"a"},
                        Arrays.asList(Arrays.asList("a"))
                )
        );
    }
}

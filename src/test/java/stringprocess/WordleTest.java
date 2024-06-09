package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WordleTest {
    private static final String GRAY_TILE = "⬜";
    private static final String YELLOW_TILE = "\uD83D\uDFE8";
    private static final String GREEN_TILE = "\uD83D\uDFE9";

    // ⬜
    // 🟩
    // 🟨
    @ParameterizedTest
    @CsvSource({
            "epoxy,⬜⬜\uD83D\uDFE9⬜⬜",
            "loose,⬜⬜\uD83D\uDFE9⬜⬜",
            "ooooo,⬜⬜\uD83D\uDFE9⬜⬜",
            "words,\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8\uD83D\uDFE8⬜",
            "drown,\uD83D\uDFE8\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9⬜",
            "crowd,\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9\uD83D\uDFE9"
    })
    void test(String input, String expected) {
        String answer = "crowd";
        assertThat(compareLetter(answer, input)).isEqualTo(expected);
    }

    static class Letter {
        private int position;
        private int value;
    }

    /**
     * 단어 비교 기능
     * - 각 자리의 단어가 같은지 체크하는 기능
     * - 자리가 같지 않아도 단어가 포함되는지 체크하는 기능 (개수도 체크가 되어야 한다.)
     * - 우선순위는 1번이므로 하나씩 덮어쓰면 된다
     */
    private String compareLetter(String answer, String input) {
        char[] answerArr = answer.toCharArray();
        char[] inputArr = input.toCharArray();
        String[] result = new String[5];

        checkContainsValue(answerArr, inputArr, result);
        return String.join("", result);
    }

    /**
     * 포함하지 않으면 회색
     * 포함하면 노란색(그런데 개수도 같아야 함)
     */
    private void checkContainsValue(char[] answerArr, char[] inputArr, String[] result) {
        Map<Character, Integer> answerCharacterCountMap = createCharacterCountMap(answerArr);

        // 초록색 먼저하고 선차감
        for (int i = 0; i < 5; i++) {
            char input = inputArr[i];
            if (answerArr[i] == input) {
                answerCharacterCountMap.put(input, answerCharacterCountMap.get(input) - 1);
                result[i] = GREEN_TILE;
            }
        }

        for (int i = 0; i < 5; i++) {
            char input = inputArr[i];
            if (answerCharacterCountMap.containsKey(input) && answerCharacterCountMap.get(input) > 0) {
                answerCharacterCountMap.put(input, answerCharacterCountMap.get(input) - 1);
                result[i] = YELLOW_TILE;
            } else if (answerCharacterCountMap.containsKey(input) && answerCharacterCountMap.get(input) <= 0) {
                if (result[i] == null || !result[i].equals(GREEN_TILE)) {
                    result[i] = GRAY_TILE;
                }
            } else {
                result[i] = GRAY_TILE;
            }
        }
    }

    private Map<Character, Integer> createCharacterCountMap(char[] answerArr) {
        Map<Character, Integer> characterCountMap = new HashMap<>();
        for (char answerChar : answerArr) {
            characterCountMap.put(answerChar, characterCountMap.getOrDefault(answerChar, 0) + 1);
        }
        return characterCountMap;
    }
}

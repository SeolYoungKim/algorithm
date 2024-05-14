package stringprocess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/reorder-data-in-log-files/description/">937.ReorderDataInLogFiles</a>
 */
public class ReorderDataTest {
    /*
     * 1. 로그의 가장 앞부분은 식별자로서, 순서에 영향을 끼치지 않음
     * 2. 문자로 구성된 로그가 숫자 로그보다 앞에 오며, 문자 로그는 사전순이다
     * 3. 문자가 동일한 경우 식별자 순으로 한다
     * 4. 숫자 로그는 입력 순서대로 한다
     */
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> letterLogComparator = Comparator.comparing((String log) -> {
                    String[] logArr = log.split(" ", 2);
                    return logArr[1];
                }
        ).thenComparing((String log) -> {
            String[] logArr = log.split(" ", 2);
            return logArr[0];
        });

        List<String> letterLogs = new ArrayList<>();
        List<String> numberLogs = new ArrayList<>();

        for (String log : logs) {
            if (isNumberLog(log)) {
                numberLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }

        letterLogs.sort(letterLogComparator);
        letterLogs.addAll(numberLogs);

        return letterLogs.toArray(new String[0]);
    }

    private static boolean isNumberLog(String log) {
        String[] logArr = log.split(" ");
        return Character.isDigit(logArr[1].charAt(0));
    }

    public static Stream<Arguments> provideLogs() {
        return Stream.of(
                Arguments.of(
                        new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"},
                        new String[]{"let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"}
                ),
                Arguments.of(
                        new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"},
                        new String[]{"g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"}
                ),
                Arguments.of(
                        new String[]{"dig1 8 1 5 1", "let1 art can", "let2 art can", "let2 own kit dig", "let3 art zero"},
                        new String[]{"let1 art can", "let2 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1"}
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideLogs")
    void test(String[] input, String[] output) {
        assertThat(reorderLogFiles(input)).containsExactly(output);
    }

    @Test
    void comparatorTest() {
        Comparator<String> comparing = Comparator.comparing(
                (String log) -> {
                    String[] logArr = log.split(" ", 2);
                    return logArr[1];
                }
        );
        Queue<String> letterLogsQueue = new PriorityQueue<>(comparing);

        String str1 = "dig1 art can";
        letterLogsQueue.offer(str1);
        String str2 = "art3 art zero";
        letterLogsQueue.offer(str2);
        String str3 = "igr2 own kit dig";
        letterLogsQueue.offer(str3);

        System.out.println("letterLogsQueue = " + letterLogsQueue);
        System.out.println("com = " + comparing.compare(str1, str2));
        System.out.println("com = " + comparing.compare(str1, str3));
        System.out.println("com = " + comparing.compare(str2, str3));

        System.out.println("str1.split()=" + str1.split(" ", 2)[1]);
        System.out.println("str2.split()=" + str2.split(" ", 2)[1]);
        System.out.println("str3.split()=" + str3.split(" ", 2)[1]);
    }
}

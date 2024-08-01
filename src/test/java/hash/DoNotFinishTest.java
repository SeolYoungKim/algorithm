package hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42576">완주하지 못한 선수</a>
 */
public class DoNotFinishTest {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> completionToCount = new HashMap<>();
        for (String c : completion) {
            completionToCount.put(c, completionToCount.getOrDefault(c, 0) + 1);
        }

        return Arrays.stream(participant)
                .filter(p -> {
                    if (!completionToCount.containsKey(p)) {
                        return true;
                    }

                    Integer count = completionToCount.get(p);
                    if (count == 0) {
                        return true;
                    }

                    completionToCount.put(p, completionToCount.get(p) - 1);
                    return false;
                })
                .findAny()
                .orElse("");
    }
}

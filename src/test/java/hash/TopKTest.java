package hash;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/">347</a>
 */
public class TopKTest {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }

        return numToCount.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue(), Collections.reverseOrder()))
                .limit(k)
                .mapToInt(entry -> entry.getKey())
                .toArray();
    }
}

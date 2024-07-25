package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <a href="https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150">169</a>
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > nums.length / 2)
                .map(Entry::getKey)
                .findAny()
                .get();
    }
}

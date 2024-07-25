package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/">3</a>
 */
public class LongestSubstringTest {
    /**
     * <img src="img.png">
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> used = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            // 이미 등장했던 문자이고, 등장했던 문자가 슬라이딩 윈도우 내부에 있다면 left 위치를 등장한 문자의 index + 1 로 옮긴다.
            // 등장했던 문자가 슬라이딩 윈도우 바깥에 있다면 무시해야한다.
            if (used.containsKey(c) && left <= used.get(c)) {
                left = used.get(c) + 1;
            } else {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            used.put(c, right);
            right++;
        }

        return maxLength;
    }
}

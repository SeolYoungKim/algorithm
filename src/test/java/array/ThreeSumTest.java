package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/3sum/description/">15.3Sum</a>
 */
public class ThreeSumTest {
    /*
     * 합이 0이되는 3개의 숫자 목록을 구하라
     * - a + b + c = 0
     * - (-a) = b + c 임을 이용해서 풀 수 있다
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        int length = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            // 같은 target에 대한 연산은 수행하지 않음 (중복 답안 제거)
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // -target = left + right 인 left, right 쌍을 찾자
            int target = nums[i];
            int leftIdx = i + 1;
            int rightIdx = length - 1;
            while (leftIdx < rightIdx) {
                int leftNum = nums[leftIdx];
                int rightNum = nums[rightIdx];

                if (leftNum + rightNum < -target) {
                    leftIdx++;
                    continue;
                }

                if (leftNum + rightNum > -target) {
                    rightIdx--;
                    continue;
                }

                results.add(Arrays.asList(target, leftNum, rightNum));
                leftIdx++;

                do {
                    rightIdx--;
                } while (leftIdx < rightIdx && nums[rightIdx] == nums[rightIdx + 1]);
            }
        }

        return results;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] nums, List<List<Integer>> expected) {
        List<List<Integer>> actual = threeSum(nums);
        actual.forEach(list -> list.sort(Comparator.naturalOrder()));
        expected.forEach(list -> list.sort(Comparator.naturalOrder()));
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{-1, 0, 1, 2, -1, -4}, Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1))),
                Arguments.of(new int[]{0, 1, 1}, Arrays.asList()),
                Arguments.of(new int[]{0, 0, 0}, Arrays.asList(Arrays.asList(0, 0, 0)))
        );
    }
}

package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://shorturl.at/Zyqwk">80.RemoveDuplicatesFromSortedArray</a>
 */
public class RemoveDuplicatesFromSortedArrayTest {
    /*
     * non-decreasing order(오름차순)로 정렬된 정수 배열 nums
     * 각 요소가 최대 두 번 나타나도록 일부 중복을 제자리 제거
     * 요소의 상대적 순서는 동일하게 유지되어야 함
     * 길이를 반환하고, nums 자체를 수정함
     * 1 <= nums.length <= 30,000
     */
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int number = nums[0];
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (number == nums[i]) {
                count++;
                if (count >= 3) {
                    nums[i] = Integer.MAX_VALUE;
                }
            } else {
                number = nums[i];
                count = 1;
            }
        }

        Arrays.sort(nums);
        return (int) Arrays.stream(nums)
                .filter(num -> num != Integer.MAX_VALUE)
                .count();
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] nums, int expected) {
        assertThat(removeDuplicates(nums)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1, 2, 2, 3}, 5),
                Arguments.of(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, 7)
        );
    }
}

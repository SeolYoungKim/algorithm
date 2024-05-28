package array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/">121.BestTimeToBuyAnsSellStock</a>
 * <p>해당 문제는 시각화를 하면 더 쉽다.</p>
 * <pre>
 *     <img src="img.png">
 * </pre>
 *
 */
public class BestTimeToBuyAnsSellStockTest {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int sell = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            sell = Math.max(sell, price - buy);
        }

        return sell;
    }

    @ParameterizedTest
    @MethodSource("provideParamsAndResult")
    void test(int[] prices, int expected) {
        assertThat(maxProfit(prices)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideParamsAndResult() {
        return Stream.of(
                Arguments.of(new int[]{7, 1, 5, 3, 6, 4}, 5),
                Arguments.of(new int[]{7, 2, 5, 3, 4, 1, 6}, 5),
                Arguments.of(new int[]{7, 6, 4, 3, 1}, 0)
        );
    }
}

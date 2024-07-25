package hash;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/jewels-and-stones/">771</a>
 */
public class JewalsAndStonesTest {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Integer> jewelSet = jewels.chars()
                .boxed()
                .collect(Collectors.toSet());

        return (int) stones.chars()
                .filter(jewelSet::contains)
                .count();
    }
}

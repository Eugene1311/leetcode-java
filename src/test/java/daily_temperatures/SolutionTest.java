package daily_temperatures;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void testSolution() {
        var result = solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        var expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
        Assert.assertArrayEquals(expected, result);
    }
}

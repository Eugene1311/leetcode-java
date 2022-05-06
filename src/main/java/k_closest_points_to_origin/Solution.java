package k_closest_points_to_origin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        List<int[]> sortedPoints = Arrays.stream(points)
                .sorted((p1, p2) ->
                        (int) (Math.pow(p1[0], 2) + Math.pow(p1[1], 2) - Math.pow(p2[0], 2) - Math.pow(p2[1], 2)))
                .collect(Collectors.toList());

        int[][] result = new int[k][2];
        return sortedPoints.subList(0, k).toArray(result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.kClosest(new int[][]{{1, 1}, {1, 3}, {-2, 2}}, 2)));
    }
}

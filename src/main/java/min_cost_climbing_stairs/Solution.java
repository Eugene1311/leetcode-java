package min_cost_climbing_stairs;

public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int twoStepBefore = cost[0];
        int oneStepBefore = cost[1];
        int curr;
        for (int i = 2; i < cost.length; i++) {
            curr = Math.min(twoStepBefore, oneStepBefore) + cost[i];
            twoStepBefore = oneStepBefore;
            oneStepBefore = curr;
        }
        return Math.min(oneStepBefore, twoStepBefore);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCostClimbingStairs(new int[]{0, 2, 2, 1}));
    }
}

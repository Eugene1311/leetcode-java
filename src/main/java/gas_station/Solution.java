package gas_station;

public class Solution {
    private static final int[] GAS = {
            0, 0, 0, 2
    };
    private static final int[] COST = {
            0, 0, 1, 0
    };

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] > 0 && gas[i] >= cost[i] && canCompleteCircuitFromStation(gas, cost, i)) {
                return i;
            }
        }

        return -1;
    }

    private boolean canCompleteCircuitFromStation(int[] gas, int[] cost, int index) {
        int tank = 0;

        for (int i = index, j = 0; j < gas.length; j++, i = gas.length - j == index ? 0 : i + 1) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{2, 3, 4, 5, 1}));
        System.out.println(solution.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(solution.canCompleteCircuit(GAS, COST));
    }
}

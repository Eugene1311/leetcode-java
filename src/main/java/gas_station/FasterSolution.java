package gas_station;

public class FasterSolution {
    private static final int[] GAS = {
            0, 0, 0, 2
    };
    private static final int[] COST = {
            0, 0, 1, 0
    };

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentTank = 0;
        int totalTank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i];
            currentTank += gas[i] - cost[i];
            if (currentTank < 0) {
                start = i + 1;
                currentTank = 0;
            }
        }

        return totalTank < 0 ? -1 : start;
    }

    public static void main(String[] args) {
        FasterSolution solution = new FasterSolution();
        System.out.println(solution.canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{2, 3, 4, 5, 1}));
        System.out.println(solution.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(solution.canCompleteCircuit(GAS, COST));
    }
}

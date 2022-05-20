package two_city_scheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length / 2;
        List<int[]> lessACosts = Arrays.stream(costs)
                .filter(cost -> cost[0] < cost[1])
                .collect(Collectors.toList());
        int lessACostsCount = lessACosts.size();

        int aCosts;
        int bCosts;

        List<int[]> costsList = Arrays.stream(costs)
                .collect(Collectors.toList());

        if (lessACostsCount <= n) {
            aCosts = lessACosts.stream()
                    .map(cost -> cost[0])
                    .mapToInt(x -> x)
                    .sum();

            List<int[]> sortedByDiff = costsList.stream()
                    .filter(cost -> cost[0] >= cost[1])
                    .sorted(Comparator.comparingInt(a -> (a[0] - a[1])))
                    .collect(Collectors.toList());

            aCosts += sortedByDiff.subList(0, n - lessACostsCount).stream()
                    .map(cost -> cost[0])
                    .mapToInt(x -> x)
                    .sum();

            bCosts = sortedByDiff.subList(n - lessACostsCount, sortedByDiff.size()).stream()
                    .map(cost -> cost[1])
                    .mapToInt(x -> x)
                    .sum();
        } else {
            List<int[]> lessBCosts = Arrays.stream(costs)
                    .filter(cost -> cost[1] < cost[0])
                    .collect(Collectors.toList());
            int lessBCostsCount = lessBCosts.size();

            bCosts = lessBCosts.stream()
                    .map(cost -> cost[1])
                    .mapToInt(x -> x)
                    .peek(cost -> System.out.println("bCost: " + cost))
                    .sum();

            List<int[]> sortedByDiff = costsList.stream()
                    .filter(cost -> cost[1] >= cost[0])
                    .sorted((a, b) -> (a[1] - a[0]) - (b[1] - b[0]))
                    .collect(Collectors.toList());

            bCosts += sortedByDiff.subList(0, n - lessBCostsCount).stream()
                    .map(cost -> cost[1])
                    .mapToInt(x -> x)
                    .sum();

            aCosts = sortedByDiff.subList(n - lessBCostsCount, sortedByDiff.size()).stream()
                    .map(cost -> cost[0])
                    .mapToInt(x -> x)
                    .sum();
        }

        return aCosts + bCosts;
    }
}

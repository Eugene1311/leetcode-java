package combination_sum;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // [2, 3, 5, 6, 7] 8
        // 7 - 1 - no
        // 6 - 2 - yes
        // 5 - 3 - yes
        // 3 - 2 -> (5 -3) -yes
        // 3 - 3
        Map<Integer, List<Integer>> intToLeftInts = new HashMap<>();

        List<Integer> copiedCandidates = Arrays.stream(candidates)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        Collections.reverse(copiedCandidates);

        for (int i = 0; i < candidates.length; i++) {
            Integer current = copiedCandidates.get(i);
            if (current == target) {
                intToLeftInts.put(current, List.of());
                continue;
            }

            for (int j = copiedCandidates.size() - 1; j >= i; j--) {
                Integer value = copiedCandidates.get(j);
                int difference = target - (current + value);

//                if (value == 1) {
//                    Integer[] numbers = new Integer[power];
//                    Arrays.fill(numbers, value);
//
//                    intToLeftInts.put(current, Arrays.asList(numbers));
//                    intToLeftInts.put(current, List.of(value));
//                }

                if (difference > target) {
                    break;
                } else if (difference == 0) {
                    intToLeftInts.put(current, List.of(value));
                } else if (intToLeftInts.containsKey(current + value)) {
                    ArrayList<Integer> integers = new ArrayList<>(intToLeftInts.get(current + value));
                    integers.add(value);
                    intToLeftInts.put(current, integers);
                } else if ((target - current) > 0 && (target - current) % value == 0) {
                    int power;
                    if (value == 1) {
                        power = target - current;
                    } else {
                        power = (int) Math.round(Math.log(target - current) / Math.log(value));
                    }
                    Integer[] numbers = new Integer[power];
                    Arrays.fill(numbers, value);

                    intToLeftInts.put(current, Arrays.asList(numbers));
                }
            }
        }
        return intToLeftInts.entrySet().stream()
                .map(entry -> {
                    ArrayList<Integer> result = new ArrayList<>();
                    result.add(entry.getKey());
                    result.addAll(entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());

//        for (int i = 0; i < candidates.length; i++) {
//            int current = candidates[i];
//            if (target % current == 0) {
//                int power = (int) Math.round(Math.log(target) / Math.log(current));
//                Integer[] numbers = new Integer[power];
//                Arrays.fill(numbers, current);
//
//                results.add(Arrays.asList(numbers));
//            }
//        }

//        return results.stream()
//                .distinct()
//                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(new int[]{1, 2}, 4));
    }
}

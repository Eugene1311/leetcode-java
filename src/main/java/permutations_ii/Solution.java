package permutations_ii;

import java.util.*;

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();

        Set<Integer> uniqueNumsSet = new HashSet<>();
        List<Integer> notUniqueNums = new ArrayList<>();
        for (int num : nums) {
            if (uniqueNumsSet.contains(num)) {
                notUniqueNums.add(num);
            }
            uniqueNumsSet.add(num);
        }

        int[] uniqueNums = uniqueNumsSet.stream()
                .mapToInt(x -> x)
                .toArray();
        processUniqueNums(uniqueNums, tempList, results, uniqueNums.length);

        return addNotUniqueNums(notUniqueNums, results);
    }

    private void processUniqueNums(int[] nums, List<Integer> tempList, List<List<Integer>> results, int resultSize) {
        if (tempList.size() == resultSize) {
            results.add(new ArrayList<>(tempList));
        } else {
            for (int num : nums) {
                if (tempList.contains(num)) {
                    continue;
                }
                tempList.add(num);
                processUniqueNums(nums, tempList, results, resultSize);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private List<List<Integer>> addNotUniqueNums(List<Integer> notUniqueNums, List<List<Integer>> results) {
        List<List<Integer>> newResults = new ArrayList<>(results);
        for (int notUniqueNum : notUniqueNums) {
            newResults = newResults.stream()
                    .flatMap(result -> {
                        List<List<Integer>> newR = new ArrayList<>();
                        for (int j = 0; j < result.size(); j++) {
                            List<Integer> newResult = new ArrayList<>(result);
                            if (result.get(j) != notUniqueNum) {
                                newResult.add(j, notUniqueNum);
                                newR.add(newResult);
                            }
                        }
                        result.add(notUniqueNum);
                        newR.add(result);
                        return newR.stream();
                    })
                    .distinct()
                    .toList();
        }

        return newResults;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3}));
        System.out.println(solution.permuteUnique(new int[]{2, 2, 1, 1}));
    }
}

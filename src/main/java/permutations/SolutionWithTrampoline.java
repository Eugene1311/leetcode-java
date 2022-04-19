package permutations;

import java.util.ArrayList;
import java.util.List;

public class SolutionWithTrampoline {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            List<Integer> result = process(nums, tempList, nums.length);
            System.out.println(result);
            results.add(result);
        }

        return results;
    }

    private List<Integer> process(int[] nums, List<Integer> tempList, int resultSize) {
        if (tempList.size() == resultSize) {
            return new ArrayList<>(tempList);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }
                List<Integer> newList = new ArrayList<>(tempList);
                newList.add(nums[i]);
                return process(nums, newList, resultSize);
            }
        }
        return List.of();
    }

    private void findPermutation(int[] nums, List<Integer> permutation) {
        for (int i = 0; i < nums.length; i++) {
            if (permutation.contains(nums[i])) {
                continue;
            }
            permutation.add(nums[i]);
            findPermutation(nums, permutation);
            permutation.remove(permutation.size() - 1);
        }
    }

    public static void main(String[] args) {
        SolutionWithTrampoline solution = new SolutionWithTrampoline();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }
}

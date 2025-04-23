package number_of_unique_xor_triplets_ii;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int uniqueXorTriplets(int[] nums) {
        Set<Integer> uniqueDuplets = new HashSet<>();

        for (int j = 0; j < nums.length; j++) {
            for (int k = j; k < nums.length; k++) {
                uniqueDuplets.add(nums[j] ^ nums[k]);
            }
        }

        int counter = 0;
        boolean[] uniqueTriplets = new boolean[2048];
        for (int num : nums) {
            for (int unique : uniqueDuplets) {
                int triplet = num ^ unique;
                if (uniqueTriplets[triplet]) {
                    continue;
                }
                uniqueTriplets[triplet] = true;
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniqueXorTriplets(new int[]{6, 7, 8, 9, 10}));
    }
}

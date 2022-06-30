package maximum_xor_for_each_query;

import java.util.Arrays;

class Solution {
  public int[] getMaximumXor(int[] nums, int maximumBit) {
    int k = (int) (Math.pow(2, maximumBit) - 1);

    int[] result = new int[nums.length];
    var current = 0;
    for (int i = 0; i < nums.length; i++) {
      current = nums[i] ^ current;
      result[nums.length - 1 - i] = k ^ current;
    }

    return result;
  }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.getMaximumXor(new int[]{0, 1, 2, 2, 5, 7}, 3)));
    }
}

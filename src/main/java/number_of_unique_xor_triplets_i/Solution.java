package number_of_unique_xor_triplets_i;

public class Solution {
    public int uniqueXorTriplets(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }

        final int maxNum = nums.length;
        int power = 0;
        int currentNum = maxNum;

        // get max power of 2
        while ((currentNum >> 1) > 0) {
            power++;
            currentNum = currentNum >> 1;
        }
        return getMaxPossibleNum(power) + 1; // plus zero
    }

    private int getMaxPossibleNum(int power) {
        int result = (int) Math.pow(2, power);

        while (power >= 0) {
            power--;
            result += (int) Math.pow(2, power);
        }

        return result;
    }
}

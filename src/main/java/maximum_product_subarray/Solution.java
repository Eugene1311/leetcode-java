package maximum_product_subarray;

public class Solution {
    public int maxProduct(int[] nums) {
        int product = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int currentProduct = 1;
            for (int j = i; j < nums.length; j++) {
                currentProduct *= nums[j];
                product = Math.max(product, currentProduct);
            }
        }

        return product;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
    }
}

package fruit_into_baskets;

public class Solution {
    public int totalFruit(int[] fruits) {
        if (fruits.length < 3) {
            return fruits.length;
        }

        int result = 1;

        for (int i = 0; i < fruits.length - 2; i++) {
            int first = fruits[i];
            Integer second = null;
            int currentResult = 0;
            for (int j = i; j < fruits.length; j++) {
                if (second != null && fruits[j] != second && fruits[j] != first) {
                    break;
                }
                if (fruits[j] != first) {
                    second = fruits[j];
                }
                currentResult++;
            }
            result = Math.max(result, currentResult);
            if (result == fruits.length - i) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalFruit(new int[]{1, 2, 1}));
        System.out.println(solution.totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(solution.totalFruit(new int[]{1, 2, 3, 2, 2}));
    }
}

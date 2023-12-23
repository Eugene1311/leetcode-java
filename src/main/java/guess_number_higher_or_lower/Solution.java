package guess_number_higher_or_lower;

class GuessGame {
    protected int guess(int picked) {
        int num = 2147_483_647;
        return Integer.compare(num, picked);
    }
}

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int min = 1;
        int max = n;
        int result = (max - min) / 2;
        int x;
        while ((x = guess(result)) != 0) {
            if (x == 1) {
                min = result;
                result += (max- min + 1) / 2;
            } else if (x == -1) {
                max = result;
                result -= (max - min + 1) / 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.guessNumber(2147483647));
    }
}

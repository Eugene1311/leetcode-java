package guess_number_higher_or_lower;

class GuessGame {
    protected int guess(int picked) {
        int num = 2;
        return Integer.compare(num, picked);
    }
}

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int result = n / 2;
        int x;
        int min = 0;
        int max = n;
        while ((x = guess(result)) != 0) {
            System.out.println("x: " + x + ", res: " + result);
            if (x == 1) {
                min = result;
                int diff = (max - result) / 2 != 0 ? (max - result) / 2 : 1;
                result += diff;
            } else if (x == -1) {
                max = result;
                result -= (result - min) / 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.guessNumber(2));
    }
}

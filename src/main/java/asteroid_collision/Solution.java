package asteroid_collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> positiveAsteroids = new Stack<>();

        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                positiveAsteroids.add(asteroid);
            } else {
                boolean battleFinished = false;
                while (!positiveAsteroids.isEmpty() && !battleFinished) {
                    Integer positiveAsteroid = positiveAsteroids.peek();
                    if (positiveAsteroid > Math.abs(asteroid)) {
                        battleFinished = true;
                    } else if (positiveAsteroid == Math.abs(asteroid)) {
                        battleFinished = true;
                        positiveAsteroids.pop();
                    } else {
                        positiveAsteroids.pop();
                    }
                }
                if (!battleFinished) {
                    result.add(asteroid);
                }
            }
        }

        return Stream.concat(
                        result.stream(),
                        positiveAsteroids.stream()
                )
                .mapToInt(x -> x)
                .toArray();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[]{5, 10, -5})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[]{10, 2, -5})));
    }
}

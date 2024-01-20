package asteroid_collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> toRightAsteroids = new Stack<>();

        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                toRightAsteroids.add(asteroid);
            } else {
                boolean battleFinished = false;
                while (!toRightAsteroids.isEmpty() && !battleFinished) {
                    Integer toRightAsteroid = toRightAsteroids.pop();
                    if (toRightAsteroid > Math.abs(asteroid)) {
                        battleFinished = true;
                        toRightAsteroids.add(toRightAsteroid);
                    } else if (toRightAsteroid == Math.abs(asteroid)) {
                        battleFinished = true;
                    }
                }
                if (!battleFinished) {
                    result.add(asteroid);
                }
            }
        }

        result.addAll(new ArrayList<>(toRightAsteroids));

        return result.stream()
                .mapToInt(x -> x)
                .toArray();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[]{5, 10, -5})));
        System.out.println(Arrays.toString(solution.asteroidCollision(new int[]{10, 2, -5})));
    }
}

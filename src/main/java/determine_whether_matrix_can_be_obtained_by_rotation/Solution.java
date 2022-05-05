package determine_whether_matrix_can_be_obtained_by_rotation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        Set<Integer> rotationCounts = new HashSet<>(Set.of(1, 2, 3, 4));
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                Set<Integer> possibleRotations = findPossibleRotations(mat, target, i, j);
                rotationCounts = rotationCounts.stream()
                        .filter(possibleRotations::contains)
                        .collect(Collectors.toSet());
                if (rotationCounts.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Set<Integer> findPossibleRotations(int[][] matrix, int[][] target, int i, int j) {
        Set<Integer> result = new HashSet<>();
        int lastIndex = matrix.length - 1;

        if (matrix[i][j] == target[i][j]) {
            result.add(4);
        }
        if (matrix[i][j] == target[j][lastIndex - i]) {
            result.add(1);
        }
        if (matrix[i][j] == target[lastIndex - i][lastIndex - j]) {
            result.add(2);
        }
        if (matrix[i][j] == target[lastIndex - j][i]) {
            result.add(3);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findRotation(new int[][]{{0, 1}, {1, 1}}, new int[][]{{1, 0}, {0, 1}}));
    }
}

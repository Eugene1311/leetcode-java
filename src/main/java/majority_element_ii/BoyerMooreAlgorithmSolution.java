package majority_element_ii;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class BoyerMooreAlgorithmSolution {
    public List<Integer> majorityElement(int[] nums) {
        Integer firstCandidate = null;
        Integer secondCandidate = null;

        int firstCounter = 0;
        int secondCounter = 0;
        for (int num : nums) {
            if (firstCounter == 0 && (secondCandidate == null || num != secondCandidate)) {
                firstCandidate = num;
                firstCounter = 1;
            } else if (secondCounter == 0 && num != firstCandidate) {
                secondCandidate = num;
                secondCounter = 1;
            } else if (num == firstCandidate) {
                firstCounter++;
            } else if (num == secondCandidate) {
                secondCounter++;
            } else {
                firstCounter--;
                secondCounter--;
            }
        }

        int size = nums.length / 3;
        firstCounter = 0;
        secondCounter = 0;
        for (int num : nums) {
            if (num == firstCandidate) {
                firstCounter++;
            }
            if (secondCandidate != null && num == secondCandidate) {
                secondCounter++;
            }
        }

        if (firstCounter <= size) {
            firstCandidate = null;
        }
        if (secondCounter <= size) {
            secondCandidate = null;
        }

        return Stream.of(firstCandidate, secondCandidate)
                .filter(Objects::nonNull)
                .toList();
    }

    public static void main(String[] args) {
        BoyerMooreAlgorithmSolution solution = new BoyerMooreAlgorithmSolution();
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));
        System.out.println(solution.majorityElement(new int[]{1}));
        System.out.println(solution.majorityElement(new int[]{2, 2, 2, 3, 3, 3, 1, 1}));
        System.out.println(solution.majorityElement(new int[]{2, 1, 1, 3, 1, 4, 5, 6}));
        System.out.println(solution.majorityElement(new int[]{3, 3, 1, 1, 1, 1, 2, 4, 4, 3, 3, 3, 4, 4}));
        System.out.println(solution.majorityElement(new int[]{0, 0, 0}));
    }
}

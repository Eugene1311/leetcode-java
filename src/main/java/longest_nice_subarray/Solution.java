package longest_nice_subarray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Solution {
    public int longestNiceSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int currentNicesLength = 1;
        int currentOR = nums[0];
        int leftBoundary = 0;

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] & currentOR) == 0) {
                currentOR = currentOR | nums[i];
                max = Math.max(max, ++currentNicesLength);
            } else {
                leftBoundary = leftBoundary < nums.length - 1 ? leftBoundary + 1 : nums.length - 1;
                i = leftBoundary;
                currentOR = nums[leftBoundary];
                currentNicesLength = 1;
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        System.out.println(solution.longestNiceSubarray(new int[]{1, 3, 8, 48, 10}));
        System.out.println(solution.longestNiceSubarray(new int[]{3, 1, 5, 11, 13}));
        System.out.println(solution.longestNiceSubarray(new int[]{744437702, 379056602, 145555074, 392756761, 560864007, 934981918, 113312475, 1090, 16384, 33, 217313281, 117883195, 978927664}));
        System.out.println(solution.longestNiceSubarray(new int[]{904163577, 321202512, 470948612, 490925389, 550193477, 87742556, 151890632, 655280661, 4, 263168, 32, 573703555, 886743681, 937599702, 120293650, 725712231, 257119393}));
        System.out.println(solution.longestNiceSubarray(new int[]{84139415, 693324769, 614626365, 497710833, 615598711, 264, 65552, 50331652, 1, 1048576, 16384, 544, 270532608, 151813349, 221976871, 678178917, 845710321, 751376227, 331656525, 739558112, 267703680}));

        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/longest-nice-subarray/test-data.txt")));
        System.out.println(solution.longestNiceSubarray(Arrays.stream(data.split("\\s")).mapToInt(Integer::parseInt).toArray()));

        System.out.println(solution.longestNiceSubarray(new int[]{8, 4096, 524288, 16777216, 2097152, 1024, 4194304, 32768, 1048576, 65536, 4, 536870912, 1, 134217728, 128, 256, 8388608, 2, 8192, 2048, 16384, 16, 64, 33554432, 131072, 512, 262144, 32, 67108864}));
    }
}

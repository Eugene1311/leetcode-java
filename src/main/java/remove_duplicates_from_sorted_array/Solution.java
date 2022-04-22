package remove_duplicates_from_sorted_array;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int removeDuplicates(int[] nums) {
        List<Integer> duplicatesIndexes = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                duplicatesIndexes.add(i + 1);
            }
        }

        for(int i = 0; i < duplicatesIndexes.size(); i++) {
            int duplicate = nums[duplicatesIndexes.get(i) - i];
            for(int j = duplicatesIndexes.get(i) - i + 1; j < nums.length; j++) {
                nums[j - 1] = nums[j];
            }
            nums[nums.length - 1] = duplicate;
        }

        return nums.length - duplicatesIndexes.size();
    }
}

package reorder_data_in_log_files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> digitLogs = new ArrayList<>();
        List<String> letterLogs = new ArrayList<>();
        for (String log: logs) {
            if (log.matches("^\\S+\\s(\\d+\\s)*(\\d+)")) {
                digitLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }

        letterLogs.sort((a, b) -> {
            int i = a.replaceFirst("^\\w+", "").compareTo(b.replaceFirst("^\\w+", ""));
            if (i == 0) {
                return a.split(" ")[0].compareTo(b.split(" ")[0]);
            }
            return i;
        });

        letterLogs.addAll(digitLogs);
        String[] result = new String[letterLogs.size()];
        return letterLogs.toArray(result);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(
            solution.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})
        ));
    }
}

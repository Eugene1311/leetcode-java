package assesstement_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> digitLogs = new ArrayList<>();
        List<String> letterLogs = new ArrayList<>();
        for (String log : logs) {
            boolean isDigit = Arrays.stream(log.split(" "))
                    .skip(1)
                    .allMatch(word -> word.matches("\\d+"));

            if (isDigit) {
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
        System.out.println(Arrays.toString(solution.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})));
        System.out.println(Arrays.toString(solution.reorderLogFiles(new String[]{"1 n u", "r 527", "j 893", "6 14", "6 82"})));
        System.out.println(Arrays.toString(solution.reorderLogFiles(new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"})));
        System.out.println(Arrays.toString(solution.reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art zero can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})));
    }
}

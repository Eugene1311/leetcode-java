package maximum_xor_with_an_element_from_array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Trie {
    private final TrieNode root = new TrieNode();

    public Trie() {
    }

    public void insert(int value) {
        TrieNode current = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = (value >> i) & 1;
            if (current.getNodes()[bit] == null) {
                current.getNodes()[bit] = new TrieNode();
            }
            current = current.getNodes()[bit];
        }
    }

    public int search(int value) {
        int maxXOR = 0;

        TrieNode current = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = (value >> i) & 1;
            int oppositeBit = Math.abs(bit - 1);
            if (current.getNodes()[oppositeBit] == null) {
                maxXOR *= 2;
                current = current.getNodes()[bit];
            } else {
                maxXOR = maxXOR * 2 + 1;
                current = current.getNodes()[oppositeBit];
            }
        }

        return maxXOR;
    }
}

class TrieNode {
    private final TrieNode[] nodes = new TrieNode[2];

    public TrieNode[] getNodes() {
        return nodes;
    }
}

public class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] result = new int[queries.length];
        Arrays.sort(nums);
        var sortedQueries = Arrays.stream(queries)
                .sorted(Comparator.comparingInt(q -> q[1]))
                .toList();

        int currentMin = 0;
        for (int i = 0, j = 0; i < queries.length; i++, j++) {
            Trie trie = new Trie();
            int finalCurrentMin = currentMin;
            int finalJ = j;
            int[] filteredNums = IntStream.of(nums)
                    .filter(num -> num >= finalCurrentMin && num <= sortedQueries.get(finalJ)[1])
                    .toArray();
            if (filteredNums.length == 0) {
                result[i] = -1;
            } else {
                for (int num : filteredNums) {
                    trie.insert(num);
                }
                result[i] = trie.search(sortedQueries.get(finalJ)[0]);
            }
            currentMin = sortedQueries.get(j)[1];
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maximizeXor(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}})));
        System.out.println(Arrays.toString(solution.maximizeXor(new int[]{5, 2, 4, 6, 6, 3}, new int[][]{{12, 4}, {8, 1}, {6, 3}})));
    }
}

package maximum_xor_of_two_numbers_in_an_array;

import java.util.Arrays;
import java.util.List;

class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }
    public void insert(String value) {
        TrieNode current = this.root;
        for (int i = 0; i < value.length(); i++) {
            int bit = value.charAt(i) - '0';
            if (current.getNodes()[bit] == null) {
                current.getNodes()[bit] = new TrieNode();
            }
            current = current.getNodes()[bit];
        }
    }

    public int search(String value) {
        int maxXOR = 0;

        TrieNode current = this.root;
        for (int i = 0; i < value.length(); i++) {
            int bit = value.charAt(i) - '0';
            int oppositeBit = Math.abs(bit -1);
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

public class TrieSolution {
    public int findMaximumXOR(int[] nums) {
        List<String> binaryStrings = Arrays.stream(nums)
                .mapToObj(Integer::toBinaryString)
                .map(str -> padLeftZeros(str, 31))
                .toList();
        Trie trie = new Trie();

        trie.insert(binaryStrings.get(0));

        int max = 0;

        for (int i = 1; i < binaryStrings.size(); i++) {
            String str = binaryStrings.get(i);
            max = Math.max(max, trie.search(str));
            trie.insert(str);
        }

        return max;
    }

    private String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public static void main(String[] args) {
        TrieSolution solution = new TrieSolution();
        System.out.println(solution.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(solution.findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));
        System.out.println(solution.findMaximumXOR(new int[]{27, 2, 1}));
    }
}

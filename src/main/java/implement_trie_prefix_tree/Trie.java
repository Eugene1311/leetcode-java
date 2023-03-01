package implement_trie_prefix_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode(null, new ArrayList<>(), false);
    }

    public void insert(String word) {
        insert(word.toCharArray(), root);
    }

    private void insert(char[] word, TrieNode node) {
        if (word.length == 0) {
            node.setIsLast(true);
            return;
        }

        node.children().stream()
                .filter(trieNode -> word[0] == trieNode.value())
                .findFirst()
                .ifPresentOrElse(
                        nextNode -> insert(Arrays.copyOfRange(word, 1, word.length), nextNode),
                        () -> {
                            var newNode = new TrieNode(word[0], new ArrayList<>(), false);
                            node.children().add(newNode);
                            insert(Arrays.copyOfRange(word, 1, word.length), newNode);
                        }
                );
    }

    public boolean search(String word) {
        return search(word.toCharArray(), root);
    }

    private boolean search(char[] word, TrieNode node) {
        if (word.length == 0) {
            return node.isLast();
        }

        return node.children().stream()
                .filter(trieNode -> word[0] == trieNode.value())
                .findFirst()
                .map(trieNode -> search(Arrays.copyOfRange(word, 1, word.length), trieNode))
                .orElse(false);
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix.toCharArray(), root);
    }

    private boolean startsWith(char[] word, TrieNode node) {
        if (word.length == 0) {
            return true;
        }

        return node.children().stream()
                .filter(trieNode -> word[0] == trieNode.value())
                .findFirst()
                .map(trieNode -> startsWith(Arrays.copyOfRange(word, 1, word.length), trieNode))
                .orElse(false);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

final class TrieNode {
    private final Character value;
    private final List<TrieNode> children;
    private boolean isLast;

    TrieNode(Character value, List<TrieNode> children, boolean isLast) {
        this.value = value;
        this.children = children;
        this.isLast = isLast;
    }

    public Character value() {
        return value;
    }

    public List<TrieNode> children() {
        return children;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TrieNode) obj;
        return Objects.equals(this.value, that.value) &&
                Objects.equals(this.children, that.children) &&
                this.isLast == that.isLast;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, children, isLast);
    }

    @Override
    public String toString() {
        return "TrieNode[" +
                "value=" + value + ", " +
                "children=" + children + ", " +
                "isLast=" + isLast + ']';
    }

}

class Test {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie);
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("bad"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

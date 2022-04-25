package balanced_binary_tree;

public class Solution {
    // 1 ms	42.1 MB
    // brute force but works fast
    public boolean isBalanced(TreeNode root) {
        return isNodeBalanced(root);
    }

    private boolean isNodeBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        return (Math.abs(getDepth(node.left, 1) - getDepth(node.right, 1)) <= 1)
                && isNodeBalanced(node.left)
                && isNodeBalanced(node.right);
    }

    private int getDepth(TreeNode node, int initialDepth) {
        if (node == null) {
            return initialDepth - 1;
        }

        return Math.max(getDepth(node.left, initialDepth + 1), getDepth(node.right, initialDepth + 1));
    }
}

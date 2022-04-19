package search_in_a_binary_search_tree;

public class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // todo try with trampoline
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        return searchBST(root.val > val ? root.left : root.right, val);
    }
}

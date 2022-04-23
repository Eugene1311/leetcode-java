package balanced_binary_tree;

public class DFSSolution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    private int dfsHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = dfsHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = dfsHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        DFSSolution solution = new DFSSolution();
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node1, node2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2, node3, node4);
        TreeNode node6 = new TreeNode(2);
        TreeNode root = new TreeNode(1, node5, node6);
        System.out.println(solution.isBalanced(root));
    }
}

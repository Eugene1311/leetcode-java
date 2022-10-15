package diameter_of_binary_tree;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getTreesDiameters(root, 0);
    }

    private int getTreesDiameters(TreeNode node, int currentDiameter) {
        if (node == null) {
            return currentDiameter;
        }
        return Math.max(
                getTreeDiameter(node),
                Math.max(getTreesDiameters(node.left, currentDiameter), getTreesDiameters(node.right, currentDiameter))
        );
    }

    private int getTreeDiameter(TreeNode node) {
        return getSubTreeDepth(node.left, 0) + getSubTreeDepth(node.right, 0);
    }

    private int getSubTreeDepth(TreeNode node, int currentDepth) {
        if (node == null) {
            return currentDepth;
        }
        if (node.left == null && node.right == null) {
            return currentDepth + 1;
        }

        return Math.max(getSubTreeDepth(node.left, currentDepth + 1), getSubTreeDepth(node.right, currentDepth + 1));
    }

    public static void main(String[] args) {
//        [1,2,3,4,5]
        var root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        root1.left = left1;
        TreeNode right = new TreeNode(3);
        root1.right = right;
        left1.left = new TreeNode(4);
        left1.right = new TreeNode(5);
        right.left = new TreeNode(6);

        //        [1,2]
        var root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        Solution solution = new Solution();
        System.out.println(solution.diameterOfBinaryTree(root1));
        System.out.println(solution.diameterOfBinaryTree(root2));
    }
}

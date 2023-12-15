package trim_a_binary_search_tree;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode result;

        TreeNode nodeInRange = findNodeInRange(root, low, high);
        if (nodeInRange == null) {
            return null;
        }

        result = new TreeNode(nodeInRange.val);

        traverseTree(nodeInRange.left, result, low, high);
        traverseTree(nodeInRange.right, result, low, high);

        return result;
    }

    private TreeNode findNodeInRange(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        } else if  (node.val >= low && node.val <= high) {
            return node;
        } else if (node.val > high) {
            return findNodeInRange(node.left, low, high);
        } else {
            return findNodeInRange(node.right, low, high);
        }
    }

    private void traverseTree(TreeNode node, TreeNode result, int low, int high) {
        if (node == null) {
            return;
        }

        if  (node.val >= low && node.val <= high) {
            if (node.val < result.val) {
                result.left = new TreeNode(node.val);
                traverseTree(node.left, result.left, low, high);
                traverseTree(node.right, result.left, low, high);
            } else if (node.val > result.val) {
                result.right = new TreeNode(node.val);
                traverseTree(node.left, result.right, low, high);
                traverseTree(node.right, result.right, low, high);
            }
        } else {
            traverseTree(node.left, result, low, high);
            traverseTree(node.right, result, low, high);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [3,1,4,null,2]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1, null, new TreeNode(2));
        root.right = new TreeNode(4);

        TreeNode result = solution.trimBST(root, 1, 2);
        System.out.println(result);

        // [4,2,5,1,3]
        root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(5);

        result = solution.trimBST(root, 1, 4);
        System.out.println(result);
    }
}

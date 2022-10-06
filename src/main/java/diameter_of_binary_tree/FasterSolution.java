package diameter_of_binary_tree;

public class FasterSolution {
    public int dia = 0;
    public int diameter(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int lh = diameter(node.left);
        int rh = diameter(node.right);

        dia = Math.max(dia, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        diameter(root);
        return dia;
    }

    public static void main(String[] args) {
//        [1,2,3,4,5]
        var root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        root1.left = left1;

        TreeNode left2 = new TreeNode(3);
        TreeNode right1 = new TreeNode(4);
        left1.left = left2;
        left1.right = right1;

        left2.left = new TreeNode(5);
        right1.left = new TreeNode(6);

        //        [1,2]
        var root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        FasterSolution solution = new FasterSolution();
        System.out.println(solution.diameterOfBinaryTree(root1));
        solution.dia = 0;
        System.out.println(solution.diameterOfBinaryTree(root2));
    }
}

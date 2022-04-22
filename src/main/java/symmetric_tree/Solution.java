package symmetric_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        // can be simplified, https://leetcode.com/problems/symmetric-tree/discuss/33054/Recursive-and-non-recursive-solutions-in-Java
        return root == null
                || processLevel(List.of(root));
    }

    private boolean processLevel(List<TreeNode> nodes) {
        nodes.forEach(node -> System.out.println("node: " + (node == null ? "null" : node.val)));
        if (nodes.stream().allMatch(node -> Objects.isNull(node))) {
            return true;
        }

        boolean result = false;
        int toIndex = nodes.size() == 1 ? 1 : nodes.size() / 2;
        System.out.println("toIndex: " + toIndex);
        for (int i = 0; i < toIndex; i++) {
            TreeNode left = nodes.get(i);
            TreeNode right = nodes.get(nodes.size() - i - 1);
            // System.out.println("left: " + left.val);
            // System.out.println("right: " + right.val);
            result = (left == null && right == null)
                    || (left != null && right != null && left.val == right.val);
            if (!result) {
                break;
            }
        }
        System.out.println(result);

        if (!result) {
            return false;
        }

        List<TreeNode> nextLevelNodes = nodes.stream()
                .flatMap(node -> {
                    List<TreeNode> newNodes = new ArrayList<>();
                    newNodes.add(node == null ? null : node.left);
                    newNodes.add(node == null ? null : node.right);
                    return newNodes.stream();
                })
                .peek(node -> System.out.println("next: " + (node == null ? "null" : node.val)))
                .collect(Collectors.toList());
        return processLevel(nextLevelNodes);
    }
}

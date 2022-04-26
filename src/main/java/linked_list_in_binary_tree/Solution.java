package linked_list_in_binary_tree;

import balanced_binary_tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
         List<TreeNode> heads = findNodesByValue(head.val, root);

         return heads.stream()
             .anyMatch(h -> checkNode(head, h));
    }

    private List<TreeNode> findNodesByValue(int value, TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }

        List<TreeNode> result = new ArrayList<>();
        if (node.val == value) {
            result.add(node);
        }

        result.addAll(findNodesByValue(value, node.left));
        result.addAll(findNodesByValue(value, node.right));
        return  result;
    }

    private boolean checkNode(ListNode listNode, TreeNode treeNode) {
        if (listNode == null) {
            return true;
        }
        if (treeNode == null) {
            return false;
        }

        if (listNode.val == treeNode.val) {
            return checkNode(listNode.next, treeNode.left) || checkNode(listNode.next, treeNode.right);
        } else {
            return false;
        }
    }
}

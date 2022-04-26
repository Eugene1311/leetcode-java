package linked_list_in_binary_tree;

import balanced_binary_tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FasterSolution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        return checkNode(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
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

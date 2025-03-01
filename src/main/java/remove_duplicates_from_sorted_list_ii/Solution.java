package remove_duplicates_from_sorted_list_ii;

import java.util.*;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode resultList = new ListNode(0);
        ListNode currentResultNode = resultList;
        ListNode current = head;
        ListNode prev = null;

        while (current.next != null) {
            if (current.val != current.next.val && (prev == null || prev.val != current.val)) {
                currentResultNode.next = new ListNode(current.val);
                currentResultNode = currentResultNode.next;
            }

            prev = current;
            current = current.next;
        }
        if (prev == null || prev.val != current.val) {
            currentResultNode.next = new ListNode(current.val);
        }

        return resultList.next;
    }

    public static void main(String[] args) {
        ListNode head1 = buildTree(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNode head2 = buildTree(new int[]{});
        Solution solution = new Solution();
        System.out.println(solution.deleteDuplicates(head1));
        System.out.println(solution.deleteDuplicates(head2));
    }

    private static ListNode buildTree(int[] values) {
        if (values.length == 0) {
            return null;
        }
        return listToListNode(Arrays.stream(values).boxed().toList());
    }

    private static ListNode listToListNode(List<Integer> values) {
        if (values.isEmpty()) {
            return null;
        }
        ListNode head = new ListNode(values.get(0));
        ListNode prev = head;

        for (int i = 1; i < values.size(); i++) {
            prev.next = new ListNode(values.get(i));
            prev = prev.next;
        }

        return head;
    }
}

package add_two_numbers;

// https://leetcode.com/problems/add-two-numbers/
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode firstCurrent = l1;
        ListNode secondCurrent = l2;
        ListNode root = new ListNode();
        ListNode current = root;
        boolean isOverTen = false;

        while (firstCurrent != null || secondCurrent != null) {
            int sum = (firstCurrent != null ? firstCurrent.val : 0)
                    + (secondCurrent != null ? secondCurrent.val : 0)
                    + (isOverTen ? 1 : 0);

            current.val = sum % 10;

            if ((firstCurrent != null && firstCurrent.next != null) || (secondCurrent != null && secondCurrent.next != null)) {
                ListNode next = new ListNode();
                current.next = next;
                current = next;
            }

            isOverTen = sum >= 10;

            firstCurrent = firstCurrent != null ? firstCurrent.next : null;
            secondCurrent = secondCurrent != null ? secondCurrent.next : null;

            if (firstCurrent == null && secondCurrent == null && isOverTen) {
                current.next = new ListNode(1);
            }
        }
        return root;
    }
}

package add_two_numbers;

public class FastestSolution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr = null;
        ListNode result = null;
        int l1Size = getListSize(l1);
        int l2Size = getListSize(l2);
        int maxSize = Math.max(l1Size, l2Size);

        ListNode curr1 = l1;
        ListNode curr2 = l2;

        int ten = 0;
        while (maxSize > 0) {
            int x = curr1 != null ? curr1.val : 0;
            int y = curr2 != null ? curr2.val : 0;
            int sum = x + y + ten;
            if (sum > 9) {
                sum -= 10;
                ten = 1;
            } else {
                ten = 0;
            }

            if (result == null) {
                result = new ListNode(sum);
                curr = result;
            } else {
                curr.next = new ListNode(sum);
                curr = curr.next;
            }

            curr1 = curr1 != null ? curr1.next : null;
            curr2 = curr2 != null ? curr2.next : null;
            maxSize--;
        }

        if (ten > 0) {
            curr.next = new ListNode(ten);
        }

        return result;
    }

    private int getListSize(ListNode root) {
        int result = 0;
        ListNode current = root;

        while (current != null) {
            result++;
            current = current.next;
        }

        return result;
    }

    public static void main(String[] args) {
        FastestSolution solution = new FastestSolution();
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode l2 = new ListNode(4, new ListNode(5));
        ListNode result = solution.addTwoNumbers(l1, l2);
        System.out.println(result);
    }
}

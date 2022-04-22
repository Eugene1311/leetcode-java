package reverse_linked_list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Solution {
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode current = head;
        Queue<ListNode> nodes = new LinkedList<>();

        while (current.next != null) {
            nodes.add(new ListNode(current.val, current.next));
            current = current.next;
        }
        nodes.add(current);
        ListNode result = nodes.stream()
                .reduce(
                        (acc, el) -> {
                            el.next = acc;
                            return el;
                        }
                ).orElseThrow();
        nodes.peek().next = null;
        return result;

//        ListNode lastNode = nodes.pop();
//        ListNode reversedHead = new ListNode(lastNode.val);
//        ListNode reversedCurrent = reversedHead;
//
//        while (!nodes.empty()) {
//            ListNode prevNode = nodes.pop();
//            ListNode nextNode = new ListNode(prevNode.val);
//            reversedCurrent.next = nextNode;
//            reversedCurrent = nextNode;
//        }
//
//        return reversedHead;
    }

    public static void main(String[] args) {
        ListNode initNode = new ListNode(0);
        AtomicReference<ListNode> currentNode = new AtomicReference<>(initNode);
        IntStream.range(1, 5)
            .forEach(i -> {
                System.out.println(initNode);
                ListNode newNode = new ListNode(i);
                currentNode.get().next = newNode;
                currentNode.set(newNode);
            });
        System.out.println(reverseList(initNode));
    }
}

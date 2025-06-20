public class LinkedList {

    /**
     * The method returns the node that is two-thirds of the way through the list.
     * 
     * - The length of the list is calculated `n`.
     * - Target position is being calculated `trgPosition = (2 * n) / 3 - 1`
     * - Returns the node at this position (counting from 0)
     */
    public <T> Node<T> getTwoThirdsNode(Node<T> head) {
        if (head == null) {
            return null; 
        }

        int n = getListLength(head);
        if (n <= 1) {
            return null; 
        }

        int trgPosition = (2 * n) / 3 - 1; 
        if (trgPosition < 0) return null; 
        return getNodePosition(head, trgPosition); 
    }

    /**
     * The method calculates the length of a linked list.
     * - Let's start at the top of the list.
     * - Iterates to the end, incrementing the counter at each step.
     */
    private <T> int getListLength(Node<T> head) {
        int length = 0;
        Node<T> current = head;

        while (current != null) {
            length++;
            current = current.next;
        }

        return length; 
    }

    /**
     * The method returns the node at the specified position in the list.
     * 
     * - Let's start at the top of the list.
     * - We move forward `position` times (until we reach the desired position).
     */
    private <T> Node<T> getNodePosition(Node<T> head, int position) {
        Node<T> current = head;

        for (int i = 0; i < position && current != null; i++) {
            current = current.next; 
        }
        return current; 
    }
}

public class MainTest {
    public static void main(String[] args) {
        LinkedList solution = new LinkedList();
        System.out.println();
        System.out.println("Testing: ");
        System.out.println();

        System.out.println("Test 1:  0 -> 1 -> 2");
        Node<Integer> list1 = createList(new Integer[]{0, 1, 2});
        Node<Integer> result1 = solution.getTwoThirdsNode(list1);
        System.out.println("Expected: 1");
        System.out.println("Result: " + (result1 != null ? result1.data : "null"));
        System.out.println("Position: " + calculatePosition(3));
        System.out.println();
        
        System.out.println("Test 2:  0 -> 1 -> 2 -> 3");
        Node<Integer> list2 = createList(new Integer[]{0, 1, 2, 3});
        Node<Integer> result2 = solution.getTwoThirdsNode(list2);
        System.out.println("Expected: 1");
        System.out.println("Result: " + (result2 != null ? result2.data : "null"));
        System.out.println("Позиція: " + calculatePosition(4));
        System.out.println();
        
        System.out.println("Test 3: 0 -> 1 -> 2 -> 3 -> 4");
        Node<Integer> list3 = createList(new Integer[]{0, 1, 2, 3, 4});
        Node<Integer> result3 = solution.getTwoThirdsNode(list3);
        System.out.println("Expected: 2");
        System.out.println("Result: " + (result3 != null ? result3.data : "null"));
        System.out.println("Position: " + calculatePosition(5));
        System.out.println();
        
        System.out.println("--- New fictional tests ---");
        System.out.println();
        
        System.out.println("Test 4:  Empty List");
        Node<Integer> result4 = solution.getTwoThirdsNode(null);
        System.out.println("Expected: null");
        System.out.println("Result: " + (result4 != null ? result4.data : "null"));
        System.out.println();
        
        // Тест 7: Більший список
        System.out.println("Test 7:  0 -> 1 -> 2 -> 3 -> 4 -> 5");
        Node<Integer> list7 = createList(new Integer[]{0, 1, 2, 3, 4, 5});
        Node<Integer> result7 = solution.getTwoThirdsNode(list7);
        System.out.println("Expected: 3 ");
        System.out.println("Result: " + (result7 != null ? result7.data : "null"));
        System.out.println("Position: " + calculatePosition(6));
    }

private static Node<Integer> createList(Integer[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        Node<Integer> head = new Node<>(values[0]);
        Node<Integer> current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new Node<>(values[i]);
            current = current.next;
        }
        
        return head;
    }
    private static int calculatePosition(int n) {
        return (2 * n) / 3 - 1;
    }
}
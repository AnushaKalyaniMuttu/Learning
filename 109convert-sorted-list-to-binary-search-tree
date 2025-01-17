/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
        ListNode head;

         public TreeNode sortedListToBST(ListNode head) {
        // Initialize the head reference to traverse the linked list
        this.head = head;

        // Get the size of the linked list to know the number of nodes
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        // Recursively build the BST
        return sortedListToBSTHelper(0, size - 1);
    }

    private TreeNode sortedListToBSTHelper(int left, int right) {
        if (left > right) {
            return null;
        }

        // Recursively build the left subtree
        int mid = left + (right - left) / 2;
        TreeNode leftChild = sortedListToBSTHelper(left, mid - 1);

        // The root is the current node in the list (move the head pointer)
        TreeNode root = new TreeNode(head.val);
        root.left = leftChild;

        // Move the head pointer to the next element
        head = head.next;

        // Recursively build the right subtree
        root.right = sortedListToBSTHelper(mid + 1, right);

        return root;
    }

}

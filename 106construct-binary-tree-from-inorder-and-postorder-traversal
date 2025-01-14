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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start building the tree from the full inorder and postorder arrays
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 
                               0, postorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, 
                                     int inStart, int inEnd, 
                                     int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // The root is the last element in postorder
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);
        
        // Find the root in the inorder array
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        // Number of nodes in the left subtree
        int leftSize = rootIndex - inStart;
        
        // Recursively build the left and right subtrees
        root.left = buildTreeHelper(inorder, postorder, inStart, rootIndex - 1,
                                     postStart, postStart + leftSize - 1);
        root.right = buildTreeHelper(inorder, postorder, rootIndex + 1, inEnd,
                                      postStart + leftSize, postEnd - 1);
        
        return root;
    }
}

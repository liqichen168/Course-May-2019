/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildBST(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (r - l) / 2 + l;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, l, mid - 1);
        node.right = buildBST(nums, mid + 1, r);
        return node;
    }
}
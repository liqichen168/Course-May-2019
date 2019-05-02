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
    public String tree2str(TreeNode t) {
        return dfs(t);
    }
    private String dfs(TreeNode node) {
        if (node == null) return "";
        String s = "" + node.val;
        String l = dfs(node.left);
        String r = dfs(node.right);
        if (l == "" && r == "") {
            return s;
        }
        if (r == "") {
            return s + "(" + l + ")";
        }
        return s + "(" + l + ")" + "(" + r + ")";
    }
}
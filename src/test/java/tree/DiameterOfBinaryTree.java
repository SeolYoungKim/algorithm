package tree;

import tree.MaximumDepthOfBinaryTree.TreeNode;

public class DiameterOfBinaryTree {
    int longest = 0;

    public int dfs(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        longest = Math.max(longest, left + right + 2);
        return Math.max(left, right) + 1;
    }
}

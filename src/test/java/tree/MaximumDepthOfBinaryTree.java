package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    public int maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();  // 부모 노드
                if (curr.left != null) {
                    queue.offer(curr.left);  // 자식 노드를 집어넣음
                }

                if (curr.right != null) {
                    queue.offer(curr.right);  // 자식 노드를 집어넣음
                }
            }
        }

        return depth;
    }

    public int maxDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepthDfs(root.left);
        int right = maxDepthDfs(root.right);

        return Math.max(left, right) + 1;
    }
}

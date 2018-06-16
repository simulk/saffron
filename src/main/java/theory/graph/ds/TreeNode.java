package theory.graph.ds;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode<T> setLeft(TreeNode<T> left) {
        this.left = left;
        return left;
    }

    public TreeNode<T> setRight(TreeNode<T> right) {
        this.right = right;
        return right;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("TreeNode[data: %s left: %s right %s]", data, left, right);
    }
}

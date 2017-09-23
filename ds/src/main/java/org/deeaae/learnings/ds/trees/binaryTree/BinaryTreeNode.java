package org.deeaae.learnings.ds;

public class BinaryTreeNode {
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public int data;

    public BinaryTreeNode() {
        left=null;
        right=null;
    }

    public BinaryTreeNode(int data) {
        super();
        this.data=data;
    }

    @Override
    public String toString() {
        return " ["+data+"] ";
    }
}

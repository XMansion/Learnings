package org.deeaae.learnings.ds.trees.binaryTree;

import org.deeaae.learnings.ds.BinaryTreeNode;

public class Height {
    public static int height(BinaryTreeNode node){
        if(node == null){
            return 0;
        }
        return Integer.max(height(node.left),height(node.right))+1;
    }
}

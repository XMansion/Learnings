package org.deeaae.learnings.ds.trees.binaryTree;

import org.deeaae.learnings.ds.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BTUtils {
    public static void printDFS(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode nullNode = new BinaryTreeNode();
        queue.add(root);
        queue.add(nullNode);
        while (!queue.isEmpty()){
            BinaryTreeNode currentNode = queue.remove();
            if(currentNode==nullNode){
                System.out.println("");
                if(queue.isEmpty()){
                    break;
                } else {
                    queue.add(nullNode);
                }
            } else {
                if(currentNode!=null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                    System.out.print(currentNode);
                }
            }
        }
    }
}

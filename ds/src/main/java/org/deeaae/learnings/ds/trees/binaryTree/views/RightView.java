package org.deeaae.learnings.ds.trees.binaryTree.views;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.bst.BST;
import org.deeaae.learnings.ds.trees.binaryTree.sampleTrees.SampleTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RightView {
    public static void execute() {
        BinaryTreeNode ll = SampleTree.getSampleTree2();
        new BST().inOrderRecursive(ll);
        System.out.println("\n");
        HashMap<Integer,BinaryTreeNode> result = rightView(ll);
        result.forEach((k,v)-> System.out.println(v));
    }
    public static HashMap<Integer,BinaryTreeNode> rightView(BinaryTreeNode node){
        HashMap<Integer,BinaryTreeNode> leftViewMap = new HashMap<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode nullNode=new BinaryTreeNode();
        queue.add(node);
        queue.add(nullNode);
        int height =0;
        leftViewMap.put(height,node);
        height++;
        while (!queue.isEmpty()){
            while (queue.peek()!=nullNode) {
                BinaryTreeNode currentNode = queue.remove();
                if(currentNode.left!=null){
                    leftViewMap.put(height,currentNode.left);
                }
                if(currentNode.left!=null) {
                    queue.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    leftViewMap.put(height,currentNode.right);
                }
                if(currentNode.right!=null) {
                    queue.add(currentNode.right);
                }
            }
            //Removing null
            queue.remove();
            if(queue.isEmpty()){
                break;
            }
            queue.add(nullNode);
            height++;
        }
        return leftViewMap;
    }
}

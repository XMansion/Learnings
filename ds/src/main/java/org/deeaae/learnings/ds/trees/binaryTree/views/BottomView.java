package org.deeaae.learnings.ds.trees.binaryTree.views;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.bst.BST;
import org.deeaae.learnings.ds.trees.binaryTree.sampleTrees.SampleTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BottomView {
    public static void execute() {
        BinaryTreeNode ll = SampleTree.getSampleTree2();
        new BST().inOrderRecursive(ll);
        System.out.println("\n");
        HashMap<Integer,BinaryTreeNode> result = topView(ll);
        //result.forEach((k,v)-> System.out.println(v));
        result.keySet().stream().sorted().forEach(k-> System.out.println(k+" : "+result.get(k)));
    }
    static class NodeDistance{
        BinaryTreeNode node;
        int distance;


    }
    public static HashMap<Integer,BinaryTreeNode> topView(BinaryTreeNode node){
        HashMap<Integer,BinaryTreeNode> bottomViewMap = new HashMap<>();
        Queue<TopView.NodeDistance> queue = new LinkedList<>();
        TopView.NodeDistance nullNode=new TopView.NodeDistance();
        TopView.NodeDistance nd = new TopView.NodeDistance();
        nd.distance=0;
        nd.node=node;
        queue.add(nd);
        queue.add(nullNode);
        while (!queue.isEmpty()){
            while (queue.peek()!=nullNode) {
                TopView.NodeDistance nodeDistance = queue.remove();
                BinaryTreeNode currentNode = nodeDistance.node;
                //System.out.print(currentNode);
                bottomViewMap.put(nodeDistance.distance,currentNode);
                if(currentNode.left!=null) {
                    TopView.NodeDistance newNodeDistance = new TopView.NodeDistance();
                    newNodeDistance.node=currentNode.left;
                    newNodeDistance.distance=nodeDistance.distance-1;
                    queue.add(newNodeDistance);
                }
                if(currentNode.right!=null) {
                    TopView.NodeDistance newNodeDistance = new TopView.NodeDistance();
                    newNodeDistance.node=currentNode.right;
                    newNodeDistance.distance=nodeDistance.distance+1;
                    queue.add(newNodeDistance);
                }
            }
            //Removing null
            System.out.println("");
            queue.remove();
            if(queue.isEmpty()){
                break;
            }
            queue.add(nullNode);
        }
        return bottomViewMap;
    }
}

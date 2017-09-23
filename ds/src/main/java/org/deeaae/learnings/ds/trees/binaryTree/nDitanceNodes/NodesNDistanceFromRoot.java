package org.deeaae.learnings.ds.trees.binaryTree.nDitanceNodes;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.sampleTrees.SampleTree;

public class NodesNDistanceFromRoot {

    public static void executePrintNDistanceNodesFromRoot() {
        BinaryTreeNode node = SampleTree.getSampleTree1();

        System.out.println("N=1");
        printNDistanceNodesFromRoot(node,1);
        System.out.println("N=2");
        printNDistanceNodesFromRoot(node,2);
        System.out.println("N=3");
        printNDistanceNodesFromRoot(node,3);
    }

    public static void printNDistanceNodesFromRoot(BinaryTreeNode rootNode,int n) {
        if(rootNode==null){
            return;
        }
        if(n==0) {
            System.out.println(rootNode);
            return;
        }
        printNDistanceNodesFromRoot(rootNode.left,n-1);
        printNDistanceNodesFromRoot(rootNode.right,n-1);
    }
}

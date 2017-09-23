package org.deeaae.learnings.ds.trees.binaryTree.nDitanceNodes;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.sampleTrees.SampleTree;

public class NodesNDistanceFromGivenNode {

    public static void executeNodesNDistanceFromGivenNode(){
        BinaryTreeNode node = SampleTree.getSampleTree1();
        printNodesNDistanceFromGivenNode(node,new BinaryTreeNode(25),1,0);
    }

    static boolean isNodeFound = false;
    static int foundAtDepth= -1;
    public static boolean printNodesNDistanceFromGivenNode(BinaryTreeNode rootNode, BinaryTreeNode searchedNode,int n,int depth) {
        if(rootNode == null) {
            return false;
        }
        if(!isNodeFound && rootNode.data == searchedNode.data ) {
            NodesNDistanceFromRoot.printNDistanceNodesFromRoot(rootNode,n);
            foundAtDepth=depth;
            return true;
        }

        // lleftNode
        boolean nodeOnLeft=printNodesNDistanceFromGivenNode(rootNode.left,searchedNode,n,depth+1);
        if(nodeOnLeft==true){
            if(foundAtDepth-depth==n){
                System.out.println(rootNode);
            }
            NodesNDistanceFromRoot.printNDistanceNodesFromRoot(rootNode.right,n-(foundAtDepth-depth)-1);
            return true;
        }

        boolean nodeOnRight=printNodesNDistanceFromGivenNode(rootNode.right,searchedNode,n,depth+1);
        if(nodeOnRight==true){
            if(foundAtDepth-depth==n){
                System.out.println(rootNode);
            }
            NodesNDistanceFromRoot.printNDistanceNodesFromRoot(rootNode.left,n-(foundAtDepth-depth)-1);
            return true;
        }
        return false;

    }

}

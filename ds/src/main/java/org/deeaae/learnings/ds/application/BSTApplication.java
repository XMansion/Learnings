package org.deeaae.learnings.ds.application;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.arrays.FindSubarrayWithSumN;
import org.deeaae.learnings.ds.arrays.Kadanes;
import org.deeaae.learnings.ds.linkedlist.misc.CheckIfPalindrome;
import org.deeaae.learnings.ds.linkedlist.misc.ReverseLinkList;
import org.deeaae.learnings.ds.linkedlist.misc.SumOfLL;
import org.deeaae.learnings.ds.recursions.FibbonacciTailRecursion;
import org.deeaae.learnings.ds.strings.PrintAllCombinatiionsOfBrackets;
import org.deeaae.learnings.ds.trees.binaryTree.bst.BST;
import org.deeaae.learnings.ds.misc.filebackedqueue.FBQ;
import org.deeaae.learnings.ds.trees.binaryTree.nDitanceNodes.NodesNDistanceFromGivenNode;
import org.deeaae.learnings.ds.trees.binaryTree.nDitanceNodes.NodesNDistanceFromRoot;

import java.util.UUID;

public class BSTApplication {
    public static BST createDefaultBST() {

        BST bst = new BST();
        bst.insert(new BinaryTreeNode(20));
        bst.insert(new BinaryTreeNode(15));
        bst.insert(new BinaryTreeNode(25));
        bst.insert(new BinaryTreeNode(10));
        bst.insert(new BinaryTreeNode(17));
        bst.insert(new BinaryTreeNode(22));
        bst.insert(new BinaryTreeNode(29));
        bst.insert(new BinaryTreeNode(3));
        bst.insert(new BinaryTreeNode(12));
        bst.insert(new BinaryTreeNode(16));
        bst.insert(new BinaryTreeNode(18));
        bst.insert(new BinaryTreeNode(26));
        bst.insert(new BinaryTreeNode(32));
        return bst;
    }

    public static void test() {
        /*BST bst = createDefaultBST();

        System.out.println("\nBST Level order structure");
        bst.displayBST();

        System.out.println("\n\nBST In Order Recursive");
        bst.inOrderRecursive(bst.getRoot());

        System.out.println("\n\nBST Pre Order Recursive");
        bst.preOrderRecursive(bst.getRoot());

        System.out.println("\n\nBST Post Order Recursive");
        bst.postOrderRecursive(bst.getRoot());
*/
        /*BigDecimal result = new Expression("SQRT(a^2 + b^2)").with("a","2.4").and("b","9.253").eval();
        System.out.println(result);*/

        /*FBQ<String> queue = new FBQ<>();
        int n=50000;
        for(int i =1;i<=n;i++) {
            queue.push(""+i+":::"+ UUID.randomUUID().toString());
        }

        for(int i =1;i<=n;i++) {
            try {
                System.out.println(queue.get());
            }catch (Exception ex) {
                System.out.println("ERROR: "+ex.getMessage());
            }
        }*/

        //NodesNDistanceFromRoot.executePrintNDistanceNodesFromRoot();
        //NodesNDistanceFromGivenNode.executeNodesNDistanceFromGivenNode();
        //PrintAllCombinatiionsOfBrackets.executePrintAllCombinatiionsOfBrackets();
        //Kadanes.testKKadanes();
        //FindSubarrayWithSumN.executeFindSubarrayWithSumN();
        //FibbonacciTailRecursion.execute();
        //ReverseLinkList.execute();
        //CheckIfPalindrome.execute();
        SumOfLL.execute();

        System.out.println("\n\nBye.");
    }

}

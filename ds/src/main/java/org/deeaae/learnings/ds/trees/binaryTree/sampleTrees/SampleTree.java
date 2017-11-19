package org.deeaae.learnings.ds.trees.binaryTree.sampleTrees;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.bst.BST;

public class SampleTree {

    public static BinaryTreeNode getSampleTree2() {
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

        bst.insert(new BinaryTreeNode(28));
        bst.insert(new BinaryTreeNode(38));
        bst.insert(new BinaryTreeNode(35));
        bst.insert(new BinaryTreeNode(45));

        return bst.getRoot();
    }

    /*
                            20
                            /\
                           /  \
                          /    \
                         /      \
                       15        25
                       /\        /\
                      /  \      /  \
                     10    17  22  29
                     /\    /\      /\
                    3 12 16 18    26 32
                                   \   \
                                    28  38
                                        /\
                                       35 45
*/

    /*
                            20
                            /\
                           /  \
                          /    \
                         /      \
                       15        25
                       /\        /\
                      /  \      /  \
                     10    17  22  29
                     /\    /\      /\
                    3 12 16 18    26 32
     */
    public static BinaryTreeNode getSampleTree1() {
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
        return bst.getRoot();
    }
}

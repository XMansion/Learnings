package org.deeaae.learnings.ds.trees.binaryTree.bst;

import org.deeaae.learnings.ds.BinaryTreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BST {
    BinaryTreeNode root;

    public BinaryTreeNode getRoot() {
        return root;
    }

    public static final String LAST_VISITTED_NODE = "lvn";
    public static final String SEARCHED_DATA_NODE = "sdn";

    public Map<String,BinaryTreeNode> searchNode(int data) {
        return searchNode(data,root);
    }

    public Map<String,BinaryTreeNode> searchNode(int data,BinaryTreeNode rootNode) {
        // Result is a map of lastVisitedNode and node where data was found.
        Map<String,BinaryTreeNode> result = new HashMap<>();
        BinaryTreeNode lastVisitedNode = null;
        BinaryTreeNode searchedDataNode = null;

        if(rootNode == null) {
            return result;
        }

        BinaryTreeNode currentNode= rootNode;

        while (currentNode!=null) {
            if(data == currentNode.data) {
                searchedDataNode = currentNode;
                break;
            }
            lastVisitedNode = currentNode;
            if(data > currentNode.data) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        result.put(LAST_VISITTED_NODE,lastVisitedNode);
        result.put(SEARCHED_DATA_NODE,searchedDataNode);
        return result;
    }

    public BinaryTreeNode insert(BinaryTreeNode newNode) {
        root = insert(newNode,root);
        return root;
    }

    public BinaryTreeNode insert(BinaryTreeNode newNode, BinaryTreeNode rootNode) {
        // Check for the empty tree. Added node will be root node.
        if(rootNode == null) {
            rootNode=newNode;
            return rootNode;
        }
        // Else search for the node
        Map<String,BinaryTreeNode> searchResult = searchNode(newNode.data,rootNode);
        if(searchResult.get(SEARCHED_DATA_NODE)!=null) {
            //Key was already present
            System.out.println("Key was already present");
            return rootNode;
        } else {
            // New node will be added to the prev visited node
            BinaryTreeNode lastVisitedNode = searchResult.get(LAST_VISITTED_NODE);
            /*if(lastVisitedNode.right !=null || lastVisitedNode.left!=null) {
                throw new RuntimeException("Error in the code. LVN is not a leaf node");
            }*/
            if(newNode.data<lastVisitedNode.data) {
                lastVisitedNode.left=newNode;
            } else {
                lastVisitedNode.right=newNode;
            }
        }

        return rootNode;
    }

    public void inOrderRecursive(BinaryTreeNode rootNode) {
        if(rootNode==null) {
            return;
        }
        inOrderRecursive(rootNode.left);
        System.out.print("["+rootNode.data+"]--");
        inOrderRecursive(rootNode.right);
    }

    public void preOrderRecursive(BinaryTreeNode rootNode) {
        if(rootNode==null) {
            return;
        }
        System.out.print("["+rootNode.data+"]--");
        preOrderRecursive(rootNode.left);
        preOrderRecursive(rootNode.right);
    }

    public void postOrderRecursive(BinaryTreeNode rootNode) {
        if(rootNode==null) {
            return;
        }
        postOrderRecursive(rootNode.left);
        postOrderRecursive(rootNode.right);
        System.out.print("["+rootNode.data+"]--");
    }

    public boolean isLeafNode(BinaryTreeNode node) {
        if(node.left==null && node.right == null) {
            return true;
        }
        return false;
    }

    public BinaryTreeNode delete(int data) {
        return null;
    }

    public void displaySubTree(BinaryTreeNode subTree) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(subTree);
        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode=queue.remove();
            if(currentNode!=null) {
                System.out.print("["+currentNode.data+"]--");
                queue.add(currentNode.left);
                queue.add(currentNode.right);
            }
        }
    }

    public void displayBST() {
        displaySubTree(root);
    }
}

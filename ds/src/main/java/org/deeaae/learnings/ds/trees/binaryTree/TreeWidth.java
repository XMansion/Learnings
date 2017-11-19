package org.deeaae.learnings.ds.trees.binaryTree;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.sampleTrees.SampleTree;

import java.util.Arrays;

public class TreeWidth {
    public static void execute(){
        BinaryTreeNode node = SampleTree.getSampleTree1();
        int[] array = new int[Height.height(node)];
        getWidth(node,array,0);
        System.out.println("Ans");
        Arrays.stream(array).forEach(i-> System.out.println(i));


    }

    public static void getWidth(BinaryTreeNode node, int[]array, int level){
        if(node!=null){

            getWidth(node.left,array,level+1);
            array[level]++;
            getWidth(node.right,array,level+1);

        }
    }
}

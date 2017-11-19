package org.deeaae.learnings.ds.trees.binaryTree.inprepost;

import org.deeaae.learnings.ds.BinaryTreeNode;
import org.deeaae.learnings.ds.trees.binaryTree.BTUtils;

public class PostfromPreNIn {
    public static void execute(){
        /*int[] in = new int[]{4, 2, 5, 1, 3, 6};
        int[] pre = new int[]{1, 2, 4, 5, 3, 6};*/
        int[] in = new int[]{5, 10, 15, 20, 30, 40,45,50,55};
        int[] pre = new int[]{20,10,5,15,40,30,50,45,55};
        postOrder(in,0,in.length,pre,0,pre.length);
        BinaryTreeNode node = getTree(in,0,in.length,pre,0,pre.length);
        BTUtils.printDFS(node);

    }

    public static int search(int[] array, int start, int end, int searchValue){
        for(int i=start;i<end;i++){
            if(array[i]==searchValue)
                return i;
        }
        return -1;
    }

    public static BinaryTreeNode getTree(int[] in,int in_start,int in_end,int[] pre, int pre_start,int pre_end){
        BinaryTreeNode rootNode = new BinaryTreeNode();
        int root = search(in,in_start,in_end,pre[pre_start]);
        rootNode.data=pre[pre_start];
        int numberOfItemsBetweenRootAndFirstElement=root-in_start+1;
        if(root>in_start && root<in_end)
            rootNode.left=getTree(in,in_start,root,pre,pre_start+1,numberOfItemsBetweenRootAndFirstElement+pre_start);
        if(root<in_end-1)
            rootNode.right=getTree(in,root+1,in_end,pre,numberOfItemsBetweenRootAndFirstElement+pre_start,pre_end);
        return rootNode;
    }

    public static void postOrder(int[] in,int in_start,int in_end,int[] pre, int pre_start,int pre_end){
        int root = search(in,in_start,in_end,pre[pre_start]);
        //System.out.println("[SV]"+pre[pre_start]);

        if(root>in_start && root<in_end){
            int numberOfItemsBetweenRootAndFirstElement=root-in_start+1;
            //System.out.println("[LEFT] " +in_start+" | "+root+" || "+(pre_start+1)+" | " +numberOfItemsBetweenRootAndFirstElement);
            postOrder(in,in_start,root,pre,pre_start+1,numberOfItemsBetweenRootAndFirstElement+pre_start);

        }
        if(root<in_end-1){
            int numberOfItemsBetweenRootAndFirstElement=root-in_start+1;
            //System.out.println("[Right] " +(root+1)+" | "+in_end+" || "+(pre_start+numberOfItemsBetweenRootAndFirstElement)+" | " +pre_end);
            postOrder(in,root+1,in_end,pre,pre_start+numberOfItemsBetweenRootAndFirstElement,pre_end);
        }
        System.out.println("\t\t[Ans]"+pre[pre_start]);
    }
}

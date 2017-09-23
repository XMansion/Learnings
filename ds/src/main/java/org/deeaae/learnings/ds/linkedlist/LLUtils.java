package org.deeaae.learnings.ds.linkedlist;

import org.deeaae.learnings.ds.linkedlist.LinkListNode;

public class LLUtils {
    public static void printList(final LinkListNode tempNode) {
        LinkListNode node = tempNode;
        while (node!=null){
            System.out.print("-"+node+"-");
            node=node.next;
        }
        System.out.println("\n");
    }

    public static LinkListNode reverseLinkList(LinkListNode node){
        LinkListNode prevNode = null;
        LinkListNode currentNode = null;
        while (node!=null){
            currentNode=node;
            node=node.next;
            currentNode.next=prevNode;
            prevNode=currentNode;
        }
        return currentNode;
    }

    public static LinkListNode reverseLinkListRecusrion(LinkListNode node){
        if(node == null){
            return null;
        }

        if(node.next == null){
            return node;
        }

        LinkListNode temp = reverseLinkListRecusrion(node.next);
        node.next.next=node;
        node.next=null;
        return temp;
    }
}

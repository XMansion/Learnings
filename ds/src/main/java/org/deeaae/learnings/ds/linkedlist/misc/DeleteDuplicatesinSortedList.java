package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

public class DeleteDuplicatesinSortedList {
    public static void execute(){
        LinkListNode node= SampleLL.sample6();
        LLUtils.printList(node);
        node=removeDuplicates(node);
        LLUtils.printList(node);
    }
    public static LinkListNode removeDuplicates(LinkListNode node){
        if(node==null){
            return null;
        }

        if(node.next==null){
            return node;
        }

        LinkListNode probeNode = node;
        while (probeNode!=null && probeNode.data==node.data){
            probeNode=probeNode.next;
        }
        node.next=probeNode;
        removeDuplicates(probeNode);
        return node;

    }
}

package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkList {
    public static void execute(){
        LinkListNode node = SampleLL.sample1();
        LLUtils.printList(node);
        node = reverseInChunks(node,5);
        LLUtils.printList(node);
    }

    public static List<LinkListNode> reverseUptill(LinkListNode start,int n) {
        if(start==null || n==0){
            return null;
        }
        List<LinkListNode> list = new ArrayList<>(2);
        if(n==1){
            list.add(start);
            list.add(start);
            list.add(start.next);
            return list;
        }
        List<LinkListNode> result = reverseUptill(start.next,n-1);
        if(result==null){
            list.add(start);
            list.add(start);
            list.add(null);
            return list;
        }
        start.next.next=start;
        start.next=null;
        list.add(result.get(0));
        list.add(start);
        list.add(result.get(2));
        return list;
    }

    public static LinkListNode reverseInChunks(LinkListNode node, int n) {
        LinkListNode prevLastNodeOfTheChunk=null;
        LinkListNode startOfNewNode=null;
        while (true){
            List<LinkListNode> result = reverseUptill(node,n);
            if(prevLastNodeOfTheChunk!=null){
                prevLastNodeOfTheChunk.next=result.get(0);
            } else {
                startOfNewNode=result.get(0);
            }
            prevLastNodeOfTheChunk=result.get(1);
            if(result.get(2)==null){
                break;
            } else {
                node = result.get(2);
            }
        }
        return startOfNewNode;

        /*List<LinkListNode> result = reverseUptill(node,n);
        LLUtils.printList(result.get(0));
        System.out.println("\t"+result.get(0));
        System.out.println("\t"+result.get(1));
        System.out.println("\t"+result.get(2));
        List<LinkListNode> result2 = reverseUptill(result.get(2),n);
        LLUtils.printList(result2.get(0));
        System.out.println("\t"+result2.get(0));
        System.out.println("\t"+result2.get(1));
        System.out.println("\t"+result2.get(2));
        List<LinkListNode> result3 = reverseUptill(result2.get(2),n);
        LLUtils.printList(result3.get(0));
        System.out.println("\t"+result3.get(0));
        System.out.println("\t"+result3.get(1));
        System.out.println("\t"+result3.get(2));
        return result.get(0);*/

    }


}

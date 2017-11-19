package org.deeaae.learnings.ds.linkedlist.misc;

import com.sun.org.apache.regexp.internal.RE;
import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;

import java.util.Map;

public class DeleteLastOccurence {
    public static void execute(){
        LinkListNode node= SampleLL.sample1();
        LLUtils.printList(node);
        node = removeLastOccurence(node,null,1).node;
        LLUtils.printList(node);
    }

    static class Result {
        boolean isLastNodeRemoved;
        LinkListNode node;
    }

    public static Result removeLastOccurence(LinkListNode node, LinkListNode prevNode, int k){
        Result result = new Result();
        if(node==null){
            result.isLastNodeRemoved=false;
            result.node=node;
            return result;
        }
        Result response = removeLastOccurence(node.next,node,k);
        if(response.isLastNodeRemoved){
            result.isLastNodeRemoved=true;
            result.node=node;
            return result;
        } else {
            if(node.data==k){
                if(prevNode==null){
                    result.isLastNodeRemoved=true;
                    result.node=node.next;
                    return result;
                } else {
                prevNode.next=node.next;
                result.isLastNodeRemoved=true;
                result.node=node;
                return result;
                }
            }
            result.isLastNodeRemoved=false;
            result.node=node;
            return result;

        }
    }
}

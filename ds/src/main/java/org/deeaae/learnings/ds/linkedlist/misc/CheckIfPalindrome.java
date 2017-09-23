package org.deeaae.learnings.ds.linkedlist.misc;

import org.deeaae.learnings.ds.linkedlist.LLUtils;
import org.deeaae.learnings.ds.linkedlist.LinkListNode;
import org.deeaae.learnings.ds.linkedlist.sample.SampleLL;
import sun.awt.image.ImageWatched;

public class CheckIfPalindrome {

    public static void execute(){
        LinkListNode node = SampleLL.sample1();
        start=node;
        LLUtils.printList(node);
        System.out.println(checkForPalindrome(node));

        node = SampleLL.sample2();
        start=node;
        LLUtils.printList(node);
        System.out.println(checkForPalindrome(node));

        node = SampleLL.sample3();
        start=node;
        LLUtils.printList(node);
        System.out.println(checkForPalindrome(node));

    }
    public static LinkListNode start;
    public static boolean checkForPalindrome(LinkListNode current){
        if(current == null) {
            return true;
        }
        boolean result = checkForPalindrome(current.next);
        if(result==false) {
            return false;
        }
        if(start.data==current.data){
            start=start.next;
            return true;
        }
        return false;
    }
}

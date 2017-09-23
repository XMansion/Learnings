package org.deeaae.learnings.app.ironman;

import org.deeaae.learnings.ironman.FileReader;
import org.deeaae.learnings.ironman.Iterator;

public class IronmanClient {
    public void test() {
        Iterator iterator = new Iterator();
        iterator.manageKeyMap("ā\u200E–‘’“” !\"#&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\_`abcdefghijklmnopqrstuvwxyz| \u00AD°çé");
        iterator.manageKeyMap("āं\u200B️–कग’च” !\"#$%&'(न)*प+,ब-.म/0र12ल345678स9:;<=>?ि@ीAुBCDEFGHIJKLौM्NOPQRSTUŕVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ÉÒàñò");
        iterator.iterate("/home/anunay/ironman_id_prod.json");
        try {
            int cntr = 0;
            while(cntr < 10) {
                int sec = 25;
                cntr++;
                Thread.sleep(1000 * sec);
                iterator.end();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

package org.deeaae.learnings.ds.strings;

public class PrintAllCombinatiionsOfBrackets {

    /*
    Catalan Number
     n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,
     */
    public static long counter =0;
    public static void executePrintAllCombinatiionsOfBrackets(){
        counter =0;
        printCombinnations("",3,0,0);
    }

    public static void printCombinnations(String combUptillNow,int n, int open,int close){
        if(open==close && open == n){
            counter++;
            System.out.println(counter+".  "+combUptillNow);
        }
        if(open<n){
            printCombinnations(combUptillNow+"{",n,open+1,close);
        }
        if(close<open){
            printCombinnations(combUptillNow+"}",n,open,close+1);
        }
    }
}

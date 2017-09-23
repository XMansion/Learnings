package org.deeaae.learnings.ds.recursions;

public class FibbonacciTailRecursion {

    public static int funt(int n,int a, int b) {
        if(n==0) {
            return a;
        }
        if(n==1){
            return b;
        }
        return funt(n-1,b,a+b);
    }
    public static void execute(){
        for(int i=0;i<10;i++)
        System.out.println(funt(i,0,1));
    }
}

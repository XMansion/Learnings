package org.deeaae.learnings.ds.dp;

public class LongestCommonSubsequence {
    public static void execute(){
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;
        System.out.println(lcs(X,Y,m-1,n-1));
    }
    public static int lcs(char[]x, char[]y,int m,int n){
        if(m<0 || n<0 ){
            return 0;
        }
        if(x[m]==y[n]){
            System.out.println(x[m]);
            return 1 + lcs(x,y,m-1,n-1);
        }else {
            return Integer.max(lcs(x,y,m,n-1),lcs(x,y,m-1,n));
        }
    }
}

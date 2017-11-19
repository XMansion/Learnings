package org.deeaae.learnings.ds.arrays.Utils;

public class DDAUtils {
    public static void printDDA(int[][] array){
        printDDA(array,'R','C');

    }
    public static void printDDA(int[][] array,char row,char col){
        System.out.println("");
        for(int i=0;i<array.length;i++){
            if(i==0){
                System.out.print("\n  "+col+"|  ");
                for(int j=0;j<array[i].length;j++){
                    System.out.print("  "+j+ "  ");
                }
                System.out.print("\n"+row+"  |");
            }
            System.out.print("\n"+ (i) + "  |  ");
            for(int j=0;j<array[i].length;j++){
                System.out.print("  "+array[i][j]+ "  ");
            }
        }
    }
}

package org.deeaae.learnings.ds.dp;

import org.deeaae.learnings.ds.arrays.Utils.DDAUtils;

public class GoldMineProblem {
    public static void execcute(){
        int gold[][]= {
            {1, 3, 1, 5},
            {2, 2, 4, 1},
            {5, 0, 2, 3},
            {0, 6, 1, 2}
        };
        DDAUtils.printDDA(gold);
        dpGoldMine(gold);
        int gold2[][]={{10, 33, 13, 15},
            {22, 21, 04, 1},
            {5, 0, 2, 3},
            {0, 6, 14, 2}};
        System.out.println("\n========================");
        DDAUtils.printDDA(gold2);
        dpGoldMine(gold2);

    }

    public static int getValue(int[][] mine,int i, int j){
        if(i<0 || j<0){
            return 0;
        }
        if(i>=mine.length || j>=mine[0].length){
            return 0;
        }
        return mine[i][j];
    }

    public static void dpGoldMine(int[][] mine){
        int totalRows=mine.length;
        int totalColumns = mine[0].length;
        int[][] maxGold = new int[totalRows][totalColumns];
        for(int j=totalColumns-1;j>=0;j--){
            for(int i=totalRows-1;i>=0;i--){
                int moveupRight = getValue(maxGold,i-1,j+1);
                int moveRight = getValue(maxGold,i,j+1);
                int moveDownRight = getValue(maxGold,i+1,j+1);
                int max = Integer.max(moveDownRight,Integer.max(moveRight,moveupRight));
                maxGold[i][j]=max+mine[i][j];
                //System.out.println("  [ "+i+" ][ "+j+" ]  ");
                //DDAUtils.printDDA(maxGold);
            }
            //System.out.println("");
        }
        DDAUtils.printDDA(maxGold);
    }
}

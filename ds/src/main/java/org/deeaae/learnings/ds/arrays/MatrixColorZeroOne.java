package org.deeaae.learnings.ds.arrays;

public class MatrixColorZeroOne {
    public static void execute(){
        int[][] array = new int[][]{
            {1, 0, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1}
        };
        print(array);
        solve(array);
    }
    public static void solve(int[][] array){
        boolean isFirstRowToBeZero=false;
        boolean isFirstColToBeZero=false;
        for(int i=0;i<array[0].length;i++){
            if(array[0][i]==0){
                isFirstRowToBeZero=true;
                break;
            }
        }
        for(int i=0;i<array.length;i++){
            if(array[i][0]==0){
                isFirstColToBeZero=true;
                break;
            }
        }

        for(int i=1;i<array.length;i++){
            for(int j=1;j<array[0].length;j++){
                array[i][0]*=array[i][j];
                array[0][j]*=array[i][j];
            }
        }

        print(array);


        for(int i=1;i<array.length;i++) {

            for (int j = 1; j < array[0].length; j++) {
                array[i][j]=array[0][j]*array[i][0];
            }
        }
        print(array);

        for(int i=0;i<array.length;i++) {
            if (isFirstColToBeZero) {
                array[i][0] = 0;
            }else {
                array[i][0] = 1;
            }
        }

        for(int i=0;i<array[0].length;i++) {
            if (isFirstRowToBeZero) {
                array[0][i] = 0;
            }else {
                array[0][i] = 1;
            }
        }
        print(array);





    }
    public static void print(int[][] array){
        System.out.println("\n\n");
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                System.out.print("\t"+array[i][j]);
            }
            System.out.println();
        }
    }
}

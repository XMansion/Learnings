package org.deeaae.learnings.ds.dp;

import org.deeaae.learnings.ds.arrays.Utils.DDAUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Knapsack01 {
    public static void execute(){
        int[] weights = new int[] {1,3,4,5};
        int[] values = new int[] {1,4,5,7};
        int n = weights.length;
        int maxWeight = 7;
        Result result =knapsackProblem(weights,values,n,maxWeight);
        System.out.println("\n"+result);

    }
    static class Result {
        int totalWeightsPicked;
        int totalValuePicked;
        List<Integer> weightsPicked;
        List<Integer> itemsPicked;

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("ItemsPicked : ");
            itemsPicked.forEach(i->result.append(" "+i + " "));
            result.append("\n");
            result.append("WeightsPicked : ");
            weightsPicked.forEach(i->result.append(" "+i + " "));
            result.append("\n");
            result.append("Total Weight Picked : "+totalWeightsPicked+"\n");
            result.append("Total Value Picked : "+totalValuePicked + "\n");
            return result.toString();



        }
    }

    public static int[][] setup(int[] weights,int[] values,int n, final int maxWeight){
        // Col -> Max Weight Possible. Starts from 0 and move on
        // Row Items picked
        //  `
        int[][] benefits = new int[n+1][maxWeight+1];

        // benefit[0,w]=0; As we are allowed to pick no item
        // benefit[i,0]=0; As max weight is zero
        for(int i=0;i<n+1;i++){
            benefits[i][0]=0;
        }
        for(int i=0;i<maxWeight+1;i++){
            benefits[0][i]=0;
        }
        return benefits;

    }

    public static void updateMatrix(int[][] benefits,int[] weights,int[] values,int n, final int maxWeight){
        // Bottom Up approach
        /*                /
                         | benefits[i-1,w]  if weights[i]>maxWeight
          benefits[i,w]=<  max |- values[i]+benefits[i-1,w-weights[i]  [valueWhenIthItemIsPicked
                         |     |- benefits[i-1,w]                      [valueWhenIthItemIsNotPicked
                          \
         */

        for(int i=1;i<n+1;i++){
            int weightOfIthItem =  weights[i-1];
            int valueOfIthItem =  values[i-1];
            for(int w=1;w<maxWeight+1;w++){
                if(weightOfIthItem>w){
                    benefits[i][w]=benefits[i-1][w];
                } else {
                    int valueWhenIthItemIsPicked = valueOfIthItem + benefits[i - 1][w - weightOfIthItem];
                    int valueWhenIthItemIsNotPicked = benefits[i - 1][w];
                    benefits[i][w] = Integer.max(valueWhenIthItemIsPicked, valueWhenIthItemIsNotPicked);
                }
                //System.out.println("\n\n[ "+i+" ][ "+w+" ]");
                //DDAUtils.printDDA(benefits,'I','W');
            }
        }

    }

    public static void getSelectedItems(int[][]benefits, int maxWeight, int i, int w, List<Integer> selection,int[] weights){
        if(i<=1){
            return;
        }
        if(w<=1){
            return;
        }
        if(benefits[i][w]==benefits[i-1][w]){
            // We could find
            getSelectedItems(benefits,maxWeight,i-1,maxWeight,selection,weights);
        } else{
            selection.add(i-1);
            getSelectedItems(benefits,maxWeight-weights[i-1],i-1,maxWeight-weights[i-1],selection,weights);
        }
    }

    public static Result knapsackProblem(int[] weights,int[] values,int n, final int maxWeight){

        int[][] benefits = setup(weights,values,n,maxWeight);
        updateMatrix(benefits,weights,values,n,maxWeight);
        DDAUtils.printDDA(benefits,'I','W');
        List<Integer> selection = new ArrayList<>();
        getSelectedItems(benefits,maxWeight,weights.length,maxWeight,selection,weights);

        Result result = new Result();
        result.totalValuePicked=benefits[weights.length][maxWeight];
        result.itemsPicked=selection;
        result.weightsPicked=result.itemsPicked
                .stream()
                .map(index->weights[index])
                .collect(Collectors.toList());
        result.totalWeightsPicked=selection.stream().mapToInt(i->weights[i]).sum();
        result.totalValuePicked=selection.stream().mapToInt(i->values[i]).sum();
        return result;
    }
}

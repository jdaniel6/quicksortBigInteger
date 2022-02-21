import java.math.BigInteger;

import static java.lang.Math.random;

public class quicksort {
    static int[] list;
    static int avg;
    static int rec_depth=0;
    private static void populate(int num){
        list = new int[num];
        for(int i = 0; i < num; i++){
            list[i] = i + 1;
            avg += list[i];
        }
        avg /= num;
        int randomIndex = 0;
        for(int i = num-1; i > 1; i--){
            randomIndex = (int)(random()*i);
            swap(i, randomIndex);
        }
        for(Integer i: list)
            System.out.print(i+ " ");
        System.out.println("Pivot = "+ avg);
    }
    private static void swap(int a, int b){
        int temp = list[a];
        list[a]=list[b];
        list[b] = temp;
    }

    private static void quicksort(int left, int right, int pivot){
        if(left >= right){
            return;
        }
        rec_depth++;
        int i = left, j = right;
        while(i < j){
            while(list[i] <= pivot && i < j){
                i++;
            }
            while(list[j] > pivot && j > i){
                j--;
            }
            if(i < j){
                swap(i, j);
                i++;
                j--;
            }
        }
        System.out.println(left + " " + right + "Pivot = "+ pivot);
        for(Integer il: list)
            System.out.print(il+ " ");
        System.out.println();
        //quicksort(left, i-1, pivot/2);
        quicksort(i, right, pivot+pivot/2);
    }

    public static void main(String[] args){
        populate(20);
        quicksort(0, list.length - 1, avg);
        System.out.println("Sorted Array: " + list);
    }
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.random;

public class quicksortBigInteger{//} implements quicksort{
    static ArrayList<BigInteger> list = new ArrayList<>();
    static BigInteger pivot = BigInteger.ZERO;
    static BigInteger numOfElements;
    private static void readElements(String filename){
        File file = new File(filename);
        BigInteger tempBuffer;
        try{
            Scanner sc = new Scanner(file);
            numOfElements = new BigInteger(sc.next());
            while(sc.hasNext()){
                tempBuffer = new BigInteger(sc.next());
                list.add(tempBuffer);
                pivot = pivot.add(tempBuffer);                        //THIS IS RELATIVELY EFFICIENT HERE BECAUSE YOU ARE READING INDIVIDUAL ELEMENTS ANYWAY
            }
            pivot = pivot.divide(numOfElements);
        }
        catch(FileNotFoundException e){
            //sample array here
            //Fischer-Yates
            numOfElements = new BigInteger("10");
            for(BigInteger i = BigInteger.ZERO; i.compareTo(numOfElements)==-1; i = i.add(BigInteger.ONE)){
                list.add(i);
                pivot = pivot.add(i);
            }                                                       //Generated sorted array from 0 to numOfElements
            pivot = pivot.divide(numOfElements);
            shuffleFY();
        }
        System.out.println("Unsorted Array: " + list);
        System.out.println("Pivot = "+ pivot.toString());

    }

    private static void quicksort(ArrayList<BigInteger> arr, int LEFT, int RIGHT, BigInteger piv){ //need to make LEFT RIGHT i j BigInteger
        if(LEFT > RIGHT || piv.equals(BigInteger.ZERO)) {
            return;
        }
        int i = LEFT, j = RIGHT;
        while(i < j){
            while(i < j && (arr.get(i).compareTo(piv)==-1 || arr.get(i).compareTo(piv)==0)){
                i++;
            }
            while(i < j && (arr.get(j).compareTo(piv)==1)){
                j--;
            }
            if(i < j){
                swap(i, j);
                i++; j--;
            }
        }
        //System.out.println(list);
        File file = new File("output.txt");
        try{
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(piv.toString());
            br.newLine();
            br.write(String.valueOf(list));
            br.newLine();
            br.newLine();
            br.newLine();
            br.close();
            fr.close();
        }
        catch(Exception e){
            //do nothing
        }
        //System.out.println(i + " " + RIGHT);
        //System.out.println(piv.multiply(new BigInteger("3")).divide(new BigInteger("2")));
        BigInteger leftPivot = BigInteger.ZERO, rightPivot = piv;
        leftPivot = piv.divide(new BigInteger("2"));
        rightPivot = rightPivot.add(leftPivot);
        System.out.println(leftPivot + " "+ rightPivot);
        quicksort(arr, LEFT, i-1, leftPivot);
        quicksort(arr, i, RIGHT+1,rightPivot);
    }

    private static void swap(int a, int b){
        BigInteger temp = list.get(a);
        list.set(a,list.get(b));
        list.set(b,temp);
    }

    private static void shuffleFY(){
        int randomIndex = 0;
        for(int i = numOfElements.intValue()-1; i > 1; i --){
            randomIndex = (int)(random()*(i));
            swap(i,randomIndex);
        }
    }

    public static void main(String[] args){
        readElements("hw.txt");
        quicksort(list, 0, numOfElements.intValue()-1, pivot);
        System.out.println("Sorted Array: " + list);
    }

}

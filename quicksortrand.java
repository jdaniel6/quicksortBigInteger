/*
 Some code inspired by pseudocode from Wikipedia:
 https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme
 A more daring (but as of 21 FEB 2022, incomplete) implementation can be found at https://github.com/jdaniel6/quicksortBigInteger
 Code done by Jeffrey Daniel (CWID: 10469475)
 Last edited on 21 FEB 2022 8:43 PM
 Fulfills HW2 of CPE 593 by Prof Dov Kruger, Stevens Institute of Technology, Hoboken, New Jersey, USA
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.random;

public class quicksortrand {
    ArrayList<Integer> list;
    int numOfElements;

    //constructor
    quicksortrand(){
        list = new ArrayList<>();
        numOfElements = 0;
    }

    //method to read elements from a file. If file is not find, it will generate an array of numbers and shuffle them using Fischer-Yates
    private void readElements(String filename){
        File file = new File(filename);
        try{
            Scanner sc = new Scanner(file);
            numOfElements = sc.nextInt();
            while(sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
        }
        catch(FileNotFoundException e){
            for(int i = 0; i < numOfElements; i++){
                list.add(i+1);
            }
            shuffleFY();
        }
        System.out.println("Unsorted Array: " + list);
    }

    //method to shuffle the array using Fischer-Yates
    private void shuffleFY(){
        int randomInd;
        for(int i = numOfElements-1; i > 1; i--){
            randomInd = (int)(random()*i);
            swap(i, randomInd);
        }
    }

    //method to swap two elements in the array
    private void swap(int indexA, int indexB){
        int temp = list.get(indexA);
        list.set(indexA, list.get(indexB));
        list.set(indexB, temp);
    }

    //method to get a random element within the current range
    private int randomIndex(int min, int max){
        return (int)((random()*(max-min))+min);
    }

    //divides an array into two partitions depending on a pivot value
    private int findSplittingIndex(ArrayList<Integer> arr, int left, int right){
        int pivot = arr.get(randomIndex(left, right));
        int i = left - 1;
        int j = right + 1;
        while(true){
            do{
                i++;
            }while(arr.get(i) < pivot);
            do{
                j--;
            }while(arr.get(j) > pivot);
            if(i >= j)
                return j;
            swap(i,j);
        }
    }

    //recursive method to quicksort the array
    private void quicksort(ArrayList<Integer> arr, int left, int right){
        if(left >= 0 && right >= 0 && left < right){
            int p = findSplittingIndex(arr, left, right);
            quicksort(arr, left, p);
            quicksort(arr, p+1, right);
        }
    }

    //main method to call quicksort
    public static void main(String[] args){
        quicksortrand Quicksort = new quicksortrand();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Please select your option by typing the option number in:\n1. Read values from 'hw3.txt' (must be in the same directory)\n2. Generate a random array of one million integers\n3. Input values manually\n4. Exit");
            switch (sc.nextInt()) {
                case 1:
                    Quicksort.list.clear();
                    Quicksort.readElements("hw3.txt");
                    Quicksort.quicksort(Quicksort.list, 0, Quicksort.numOfElements - 1);
                    System.out.println("Sorted Array: " + Quicksort.list);
                    break;
                case 2:
                    Quicksort.list.clear();
                    Quicksort.numOfElements = 1000000;
                    Quicksort.readElements("NonExistentFileToThrowFileNotFoundExceptionOnPurpose.jpg");
                    Quicksort.quicksort(Quicksort.list, 0, Quicksort.numOfElements - 1);
                    System.out.println("Sorted Array: " + Quicksort.list);
                    break;
                case 3:
                    Quicksort.list.clear();
                    System.out.println("Enter your elements (type 'done' at any time to stop): ");
                    String s;
                    while (true) {
                        s = sc.next();
                        if (!s.equalsIgnoreCase("done"))
                            Quicksort.list.add(Integer.valueOf(s));
                        else
                            break;
                    }
                    Quicksort.numOfElements = Quicksort.list.size();
                    Quicksort.quicksort(Quicksort.list, 0, Quicksort.numOfElements - 1);
                    System.out.println("Sorted Array: " + Quicksort.list);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Incorrect option selected; type '1', '2', '3', or '4' as a valid option.");
            }
        } while (true);
    }
}

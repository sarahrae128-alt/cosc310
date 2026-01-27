package chapter6;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;

public class ArrayVsArrayListLab {

    

    protected static  int listRandomAccess(int indices[], ArrayList<Integer> list){
        int result = 0;
        for( int i = 0; i < indices.length; i++){
            result += list.get(indices[i]);
        }return result;
    }

    public static void main(String[] args) throws IOException {
        /*
        long start = System.nanoTime();
        System.out.println("hello, world");
        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("println:   " + elapsed + "ns");

        start = System.nanoTime();
        arrayTest(9_000);  
        finish = System.nanoTime();
        elapsed = finish - start;
        System.out.println("arrayTest: " + elapsed + "ns");
        */
        int arr[] = DataLoader.loadArray("numbers.txt");
        ArrayList<Integer> list = DataLoader.loadArrayList("numbers.txt");
        Random r = new Random();
        int indices[] = new int[100];
        for(int i = 0; i < indices.length; i++) {
            indices[i] = r.nextInt(arr.length);
        }

        PrintWriter fileOut = new PrintWriter(new File("result.csv"));
        Target tests[] = new Target[8];

       

   
    }
}

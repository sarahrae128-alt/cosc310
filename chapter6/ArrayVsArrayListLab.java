package chapter6;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ArrayVsArrayListLab {

    protected static int listRandomAccess(int indices[], ArrayList<Integer> list) {
        int result = 0;
        for (int i = 0; i < indices.length; i++) {
            result += list.get(indices[i]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
       /* long start = System.nanoTime();
        System.out.println("hello, world");
        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("println: " + elapsed + "ns");
        start = System.nanoTime();
        arrayTest(30_000);
        finish = System.nanoTime();
        elapsed = finish - start;
        System.out.println("arrayTest: " + elapsed + "ns"); */
        int arr[] = DataLoader.loadArray("numbers.txt");
        ArrayList<Integer> list = DataLoader.loadArrayList("numbers.txt");
        Random r = new Random();
        int indices[] = new int[100_000];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = r.nextInt(arr.length);
        }

        PrintWriter fileOut = new PrintWriter(new File("results.csv"));
        Target tests[] = new Target[8];
        double testAverages[] = new double[8];

        //u need to compare the results of the array verision of a test 
        //with the arraylist verison of the test, so you
        tests[0] = new ArrayRandom(arr, new ArrayList<>(list), "array, random_access");
        tests[1] = new ListRandom(arr, new ArrayList<>(list), "arraylist, random_access");
        tests[2] = new ArrayAppend(arr, new ArrayList<>(list), "array, append");
        tests[3] = new ListAppend(arr, new ArrayList<>(list), "arraylist, append");
        tests[4] = new ArrayInsert(arr, new ArrayList<>(list),"array, insert");
        tests[5] = new ListInsert(arr, new ArrayList<>(list),"arraylist, insert");
        tests[6] = new ArrayRemove(arr, new ArrayList<>(list),"array, removed");
        tests[7] = new ListRemove(arr, new ArrayList<>(list),"arraylist, removed");   
       
        for (int i = 0; i <tests.length; i++){
            Target target= tests[i];
            if (target != null) {
                testAverages[i] = target.runTests(indices);
                target.writeResults(fileOut);
            }
        }
        
        //the following loops determine te winner between Array vs ArrayList
        //this will be outputted into the terminal
            if (testAverages[0]<testAverages[1]){
                System.out.println("array wins! on random access with a time average of " + testAverages[0]);
            }else {
                System.out.println("lists wins! on random access with a time average of " + testAverages[1]);
            }
            if (testAverages[2]<testAverages[3]){
                System.out.println("array wins! on append with a time average of " + testAverages[2]);
            }else {
                System.out.println("lists wins! on append with a time averageof " + testAverages[3]);
            }
            if (testAverages[4]<testAverages[5]){
                System.out.println("array wins! on insert with a time average of " + testAverages[4]);
            }else {
                System.out.println("lists wins! on insert with a time average of " + testAverages[5]);
            }
            if (testAverages[6]<testAverages[7]){
                System.out.println("array wins! on removed with a time average of " + testAverages[6]);
            }else {
                System.out.println("lists wins! on removed with a time average of " + testAverages[7]);
            }
        
        fileOut.close(); 
    }
}

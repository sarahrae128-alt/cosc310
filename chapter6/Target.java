package chapter6;

import java.util.ArrayList;
import java.io.PrintWriter;

abstract public class Target {
    public static final int TRIALS = 5;
    protected int arr[];
    protected ArrayList<Integer> list;
    private String name;// must be in this format: array, random_access, etc...
    private long results[] = new long[TRIALS];


    public Target(int arr[], ArrayList<Integer> list, String name) {
        this.arr = arr;
        this.list = list;
        this.name = name;
    }

    
    //method under test
    //indicesOrNums is being used for two differnet purposes
    abstract public int method(int indicesOrNums[]);

    //calls the method under test TRAILS number of times
    //
    public double runTest(int indicesOrNums[]){
        long total = 0;
        for (int i = 0; i < TRIALS; i++){
            long start = System.nanoTime();
            long result = method(indicesOrNums);
            long end = System.nanoTime();
            long elapsed = end - start;
            results[i] = elapsed;
        }
         double avg = total / (double) TRIALS;
        return avg;   
    }
    // as well as outputs the individual results to the stream
    public void writeResuls(PrintWriter out){
        for (int i = 0; i < TRIALS; i ++){
            out.printf("%s,%d,%.2f\n", name, i+1, results[i]/1000.0);

        }
    }
}

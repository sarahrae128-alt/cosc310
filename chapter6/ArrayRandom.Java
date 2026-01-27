package chapter6;

import java.util.ArrayList;

public class ArrayRandom extends Target{

    public ArrayRandom(String name, int[] arr, ArrayList<Integer> list) {
        super(name, arr, list);
    }

    @Override
    public int method(int[] indieNums) {
        int result = 0;
        for (int i = 0; i < indieNums.length; i++) {
            result += arr[indieNums[i]];
        }
        return result;
    }
}


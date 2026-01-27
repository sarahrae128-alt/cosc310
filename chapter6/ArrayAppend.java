package chapter6;

import java.util.ArrayList;

public class ArrayAppend extends Target{

    public ArrayAppend(String name, int[] arr, ArrayList<Integer> list) {
        super(arr, list, name);
    }

    @Override
    public int method(int[] indicesOrNums) {
        int result = 0;
        int largerarr[] = java.util.Arrays.copyOf(arr, arr.length + indicesOrNums.length);
        for (int i = 0; i < indicesOrNums.length; i++) {
            result += arr[indicesOrNums[i]];
            largerarr[arr.length+i] = indicesOrNums[i];
        }
        return result;
    }
}



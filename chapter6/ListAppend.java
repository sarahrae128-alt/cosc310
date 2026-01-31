package chapter6; 
import java.util.ArrayList; 

public class ListAppend extends Target { 
    public ListAppend(int arr[], ArrayList<Integer> list, String name) { 
        super(arr, list, name); 
    } 
    @Override 
    public int method(int[] indicesOrnums) { 
        // Reset list so each trial starts fresh 
        list = new ArrayList<>(list); 
        int result = 0; 
        for (int i = 0; i < indicesOrnums.length; i++) { 
            result += list.get(indicesOrnums[i]); 
            list.add(indicesOrnums[i]); 
        } 
        return result;
    } 
}
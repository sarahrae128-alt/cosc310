package chapter14;

import java.util.ArrayList;

public class Sorting {

    private static void swap(ArrayList<Comparable>list, int i, int j){
        Comparable temp = list.get(i);
        list.set(i, list.get(i));
        list.set(j, temp);
    }
    /*
    comparable
    o1.compareTo(02)... returns -1 if o1 is "less than" o2
    o1.compareTo(o2)... returns 0 if o1 is "equal to" o2
    o1.compareTo(o2)... returns 1 if o1 is "greater than" o2
    decstructive method (i.e., if modifies the og list)
    */
    public static void selectionSort(ArrayList<Comparable> list){

        //some optimizations
        if (list.size()<2){
            return;
        } 
        if(!(list.get(0) instanceof Comparable))

        for(int i=0; i<list.size()-1; i++){
            Comparable smallest = list.get(i);
            int smallesti =i;
            for(int j = i+1; j<list.size(); j++){
                Comparable c = list.get(j);
                if(c.compareTo(smallest)<0){
                    smallest=c;
                    smallesti= j;
                
                }
            }
            swap(list, i, smallesti);
        }
        
    }
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(null);
    }
}

package chapter8;

import java.util.ArrayList;

public class Banner {   
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();
        Person persons[] = new Person[15_000];
        persons[0] = new Student("John Smith", "900000000", "123 street, city, state, zip, usa", "jsmith@samford.edu");
        persons[1] = new Staff("Jane Doe", "800000000", "456 avenue, city, state, zip, usa", "janedoe@samford.edu");
        persons[2] = new Faculty("Jim Brown", "700000000", "789 boulevard, city, state, zip, usa", "jbrown@samford.edu");
        persons[3] = new Staff("Jake White", "600000000", "101 street, city, state, zip, usa", "jwhite@samford.edu");
        persons[4] = new Staff("Jill Green", "500000000", "202 avenue, city, state, zip, usa", "jgreen@samford.edu");
        for (Person person : persons) {
            if (person != null) {
                System.out.println("Name: "+ person.getName());
                System.out.println("Type: "+ person.getType());
                
            }
        }
    }
}
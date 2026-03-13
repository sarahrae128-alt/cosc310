package chapter13;

public class Palindrome {
    public static boolean palin(String word){
            int len = word.length();
        if (len<=1)
            return true;
        else{
            char first = word.charAt(0);
            char last = word.charAt(len-1);
            if (first != last)
                return false;
            else{
                String shorter = word.substring(1, len-1).trim();
                /* this code is no longer neccessary do to the trim function
                int i;
                for(i=0; i <shorter.length()&&shorter.charAt(i)== ' '; i++);
                shorter = shorter.substring(i,shorter.length()-1);
                for(i=shorter.length()-1; i >0 &&shorter.charAt(i)== ' '; i--);
                shorter = shorter.substring(0,i);
                */
                return palin(shorter);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(palin("saippuakivikauppias"));
    }

}

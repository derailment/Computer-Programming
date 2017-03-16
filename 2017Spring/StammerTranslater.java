import java.util.*;
import java.util.regex.*;

public class StammerTranslater {

     public static void main(String []args){
        System.out.print(" Mr. Bon says, ");
        Scanner input = new Scanner(System.in);
        String  s = input.nextLine();
        s = Pattern.compile("\\A\"").matcher(s).replaceAll("");
        s = Pattern.compile("\"\\Z").matcher(s).replaceAll("");
        s = Pattern.compile("[a-zA-z]+-").matcher(s).replaceAll("");
        System.out.println("Translating...");
        System.out.println(s);
     }
}

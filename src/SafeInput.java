import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    public static int getInt(Scanner pipe, String prompt){
        int retInt = 0;
        Boolean Valid = false;

        do{
            System.out.print("\n" +prompt + ": ");
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                pipe.nextLine();
                Valid = true;
            }
            else{
                System.out.println("Invalid input");
                Valid = false;
                pipe.next();
            }
        }while(!Valid);
        return retInt;
    }
    public static double getDouble(Scanner pipe, String prompt){
        Double retDouble = 0.0;
        Boolean Valid = false;
        do{
            System.out.print("\n" +prompt + ": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                Valid = true;
            }
            else{
                System.out.println("Invalid input");
                Valid = false;
                pipe.next();
            }
        }while(!Valid);
        return retDouble;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low , int high){
        int retInt = 0;
        Boolean Valid = false;
        do{
            System.out.print("\n" +prompt + ": ");
            if(pipe.hasNextInt()){
                int Floatret;
                Floatret = pipe.nextInt();
                pipe.nextLine();
                if (Floatret >= low && Floatret <= high){
                    Valid = true;
                    retInt = Floatret;
                }
                else{
                    System.out.println("Invalid input, please enter a value within the range");
                }
            }
            else{
                System.out.println("Invalid input, please enter an integer");
                pipe.next();
            }
        }while(!Valid);

        return retInt;
    }
    public static Double getRangedDouble(Scanner pipe, String prompt, double low , double high){
        Double retDouble = 0.0;
        Boolean Valid = false;

        do {
            System.out.print("\n" +prompt + ": ");
            if(pipe.hasNextDouble()){
                Double FloatRet;
                FloatRet = pipe.nextDouble();
                pipe.nextLine();
                if (FloatRet >= low && FloatRet <= high){
                    Valid = true;
                    retDouble = FloatRet;
                }
                else{
                    System.out.println("Invalid input, please enter a value within the range");
                }
            }
            else{
                System.out.println("Invalid input, please enter a double");
                pipe.next();
            }
        }while(!Valid);

        return retDouble;
    }
    public static Boolean getYNConfirm(Scanner pipe, String prompt){
        Boolean retBool = null;
        String FloatRet;
        boolean Valid = false;
        do{
            System.out.print("\n" +prompt + ": ");
            if(pipe.hasNext()){
                FloatRet = pipe.next();
                pipe.nextLine();
                if(FloatRet.toUpperCase().equals("Y")){
                    retBool = true;
                    Valid = true;
                }
                else if (FloatRet.toUpperCase().equals("N")){
                    retBool = false;
                    Valid = true;
                }
                else{
                    System.out.println("Invalid Input, please enter a valid value");
                    Valid = false;
                }
            }
            else{
                System.out.println("Invalid input, try again");
            }
        }while(!Valid);
        return retBool;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regex){
        String retString = "";
        String intput = "";
        Boolean Valid = false;
        Pattern pattern = Pattern.compile(regex);
        do{
            System.out.print("\n" +prompt + ": ");
            intput = pipe.nextLine();
            Matcher matcher = pattern.matcher(intput);
            if(matcher.find()){
                Valid = true;
                retString = matcher.group();
            }
            else{
                System.out.println("Invalid input, please enter a valid value");
            }

        }while(!Valid);
        return retString;
    }
    public static void prettyHeader(String msg){
        msg.trim();
        int CenterWidth = msg.length();
        int TotalWidth = 60;
        int SideStars = 3;
        int Padding = (TotalWidth -((SideStars*2) + CenterWidth)) /2 ;
        for( int x = 0 ; x <= TotalWidth; x++){
            System.out.print("*");
        }
        System.out.println();
        for(int x = 0; x < SideStars; x++){
            System.out.print("*");
        }
        for(int x = 0; x <= Padding; x++){
            System.out.print(" ");
        }
        System.out.print(msg);
        for(int x = 0; x <= Padding; x++){
            System.out.print(" ");
        }
        for(int x = 0; x < SideStars; x++){
            System.out.print("*");
        }
        System.out.println();
        for(int x = 0; x <= TotalWidth; x++){
            System.out.print("*");
        }
    }
}

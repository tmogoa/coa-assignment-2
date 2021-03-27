/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbersys;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tony
 */
public class BaseConverter {

    public static void main(String[] args){
        //startProgram();
        String[] header = {"TestHeader1", "efef", "dsvdvrevre", "SCDF"};
        String[][] data = {{"23", "sevcwrvrvrwvrwvrvreverv", "45", "ewfwf"}, {"23", "sev", "45lw", "ewfwlwbcqwcbwqlcbf"}, {"2ldibasd3", "rv", "45smusvku", "ewfwfAKSUGAK"}, {"23", "sev", "45qwcqwcwqcwqcqw", "e"}};
        CLITable cliTable = new CLITable(header, data);
        cliTable.render();
    }
    
    public void startProgram(){
        while(true){
            printInstructions();
            Scanner scanner = new Scanner(System.in);
            System.out.print("~");
            String input = scanner.nextLine();
            switch(input){
                case "a":
                    break;
                case "b":
                    break;
                case "q":
                    System.exit(0);
                default:
                    try {
                        double num = Double.parseDouble(input);
                        String result = convert(num, 2);
                        System.out.println("ans:" + result);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;
            }
        }
    }
    
    public static String convert(int num, int toBase){
       if(num == 0){
           return "";
       }else{
           return convert(num / toBase, toBase) + "" + toSingleSymbol(num % toBase);
       } 
    }
    
    public static String toSingleSymbol(int num){
        switch(num){
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return Integer.toString(num);
        }
    }
    public static String convert(double num, int toBase){
       int integralPart = (int) Math.floor(num);
       double fractionPart = num - integralPart;
       String beforeRadix = convert(integralPart, toBase);
       String afterRadix  = convertFraction(fractionPart);
       if(afterRadix.length() > 5){
           afterRadix = afterRadix.substring(0, 5);
       }
       return beforeRadix + "." + afterRadix; 
    }
    
    public static String convertFraction(double fraction){
        if(fraction == 0){
            return "";
        }else{
            double fractionx2 = fraction * 2;
            boolean isFractional = (fractionx2 - 1) < 0;
            return (isFractional ? "0" : "1") + convertFraction(isFractional ? fractionx2 : fractionx2 - 1);
        }
    }
    
    public static void printInstructions(){
        System.out.println("Hi, these are the commands:\n");
        System.out.println("\t=>a - show base 2 and 16 equivalents of base 10 0-18, 31, 100, 255, 256");
        System.out.println("\t=>b - show base 2 of 30 random base 10 floating point numbers");
        System.out.println("\t=>enter any number(even floats) to get the binary equivalent of it");
        System.out.println("\t=>q - to quit");
    }
    
    public static double[] genRandom(){
        double[] doubles = new double[30];
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int integerPart = random.nextInt(1000);
            int fractionPart = random.nextInt(1000);
            doubles[i] = Double.parseDouble(integerPart + "." + fractionPart);
        }
        return doubles;
    }
}

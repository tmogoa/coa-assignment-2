/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbersys;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tony
 */
public class BaseConverter {

    private static final String[] doublesTableHeader = {"S/No.", "Decimal Number", "Binary Number", "Remarks"};
    
    public static void main(String[] args){
        startProgram();
    }
    
    public static void startProgram(){
        while(true){
            printInstructions();
            Scanner scanner = new Scanner(System.in);
            System.out.print("~");
            String input = scanner.nextLine();
            switch(input){
                case "a":
                    renderStatictable();
                    break;
                case "b":
                    renderDynamicTable();
                    break;
                case "q":
                    System.exit(0);
                default:
                    try {
                        double num = Double.parseDouble(input);
                        if(isInteger(num)){
                            String result = convert((int) num, 2);
                            System.out.println(num + " [10] => " + result + " [2]");
                        }else{
                            String[] result = convert(num, 2);
                            System.out.println(num + " [10] => " + result[0] + " [2]" + " @" + result[1]);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;
            }
        }
    }
    
    public static void renderStatictable(){
        String[] header = {"Decimal (base 10)", "Binary (base 2)", "Hexadecimal(base 16)"};
        String[][] data = new String[23][3];
        for (int i = 0; i < 19; i++) {
            String[] row = {Integer.toString(i), convert(i, 2), convert(i, 16)};
            data[i] = row;
        }
        int[] otherNums = {31, 100, 255, 256};
        for (int i = 0; i < otherNums.length; i++) {
            String[] row = {Integer.toString(otherNums[i]), convert(otherNums[i], 2), convert(otherNums[i], 16)};
            data[i + 19] = row;
        }
        CLITable cLITable = new CLITable(header, data);
        cLITable.render();
    }
    
    public static void renderDynamicTable(){
       String[][] data = new String[30][4];
       double[] randomDoubles = genRandom();
       for(int i = 0; i < randomDoubles.length; i++){
           String[] result = convert(randomDoubles[i], 2);
           String[] row = {Integer.toString(i + 1), Double.toString(randomDoubles[i]), result[0], result[1]};
           data[i] = row;
       }
       CLITable cLITable = new CLITable(doublesTableHeader, data);
       cLITable.render();
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
    
    public static String[] convert(double num, int toBase){
       int integralPart = (int) Math.floor(num);
       double fractionPart = num - integralPart;
       String beforeRadix = convert(integralPart, toBase);
       String afterRadix  = convertFraction(fractionPart);
       String remark = "Exact";
       if(afterRadix.length() > 5){
           remark = "Approximate";
           afterRadix = afterRadix.substring(0, 5);
       }
       return new String[]{beforeRadix + "." + afterRadix, remark}; 
    }
    
    public static boolean isInteger(double num){
        return num - (int) Math.floor(num) == 0;
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

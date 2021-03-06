/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numbersys;

/**
 *
 * @author tony mogoa
 * 
 * This class allows you to render a table.
 * To render a table:
 *  1. Pass the table header(array), table data(2d-array) and alignment to the constructor
 *  2. Call the render() function.
 * 
 * For the alignment pass `Alignment.RIGHT` to get right-alignment and `Alignment.LEFT` for left-alignment 
 */
public class CLITable {
    
    private final String[] header;
    private final String[][] data;
    private int[] columnLengths;
    private int tableLength = 1;
    private final Alignment alignment;
    
    public CLITable(String[] header, String[][] data, Alignment alignment){
        this.header = header;
        this.data = data;
        this.alignment = alignment;
        calculateColumnLengths();
    }
    
    private void calculateColumnLengths(){
        columnLengths = new int[header.length];
        for (int i = 0; i < data[0].length; i++) {
            int maxColumnLength = header[i].length();
            for (int j = 0; j < data.length; j++) {
                if(data[j][i].length() > maxColumnLength){
                    maxColumnLength = data[j][i].length();
                }
            }
            columnLengths[i] = maxColumnLength;
        }
    }
    
    public void render(){
        renderHeader();
        renderData();
    }
    
    public void renderHeader(){
        tableLength = 1;
        System.out.print("|");
        for (int i = 0; i < header.length; i++) {
            String stringToRender = " " + header[i];
            tableLength += stringToRender.length();
        
            if(alignment == Alignment.LEFT){
                System.out.print(stringToRender);
            }
            for (int j = 0; j <= columnLengths[i] - header[i].length(); j++) {
                System.out.print(" ");
                tableLength++;
            }
            if(alignment == Alignment.RIGHT){
               System.out.print(stringToRender);
            }
            System.out.print("|");
            tableLength++;
        }
        System.out.print("\n");
        for (int i = 0; i < tableLength; i++) {
            System.out.print("=");
        }
        System.out.print("\n");
    }
    
    public void renderData(){
        for (int i = 0; i < data.length; i++) {
            System.out.print("|");
            for (int j = 0; j < data[0].length; j++) {
                if(alignment == Alignment.LEFT){
                    System.out.print(" " + data[i][j]);
                }
                for (int k = 0; k <= columnLengths[j] - data[i][j].length(); k++) {
                    System.out.print(" ");
                }
                if(alignment == Alignment.RIGHT){
                    System.out.print(" " + data[i][j]);
                }
                System.out.print("|");
            }
            System.out.print("\n");
            for (int l = 0; l < tableLength; l++) {
                System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}

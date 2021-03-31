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
public class Table {
    
   private final String[] header;
    private final String[][] data;
    private int[] columnLengths;
    private int[] cumulativeColumnBounds;
    private int tableLength;
    private final Alignment alignment;

    public Table(String[] header, String[][] data, Alignment alignment){
        this.header = header;
        this.data = data;
        this.alignment = alignment;
        calculateColumnLengths();
    }

    private void calculateColumnLengths(){
        columnLengths = new int[header.length];
        for (int i = 0; i < data[0].length; i++) {
            int maxColumnLength = header[i].length();
            for (String[] datum : data) {
                if (datum[i].length() > maxColumnLength) {
                    maxColumnLength = datum[i].length();
                }
            }
            columnLengths[i] = maxColumnLength;
        }
        tableLength = calcTableLength();
    }

    public void render(){
        renderHeader();
        renderData();
    }

    public int calcTableLength(){
        int itableLength = 1;
        for (int columnLength: columnLengths) {
            for (int i = 0; i <= columnLength + 2; i++){
                itableLength++;
            }
        }
        return itableLength;
    }

    public void drawLine(char symbol){
        System.out.print("\n");
        for (int i = 0; i < tableLength; i++) {
            System.out.print(symbol);
        }
        System.out.print("\n");
    }

    public void renderHeader(){
        drawLine('+');
        System.out.print("|");
        for (int i = 0; i < header.length; i++) {
            String stringToRender = " " + header[i];
            //tableLength += stringToRender.length();

            if(alignment == Alignment.LEFT){
                System.out.print(stringToRender);
            }
            for (int j = 0; j <= columnLengths[i] - header[i].length(); j++) {
                System.out.print(" ");
                //tableLength++;
            }
            if(alignment == Alignment.RIGHT){
                System.out.print(stringToRender);
            }
            System.out.print("|");
            //tableLength++;
        }

        drawLine('=');
    }

    public void renderData(){
        for (String[] datum : data) {
            System.out.print("|");
            for (int j = 0; j < data[0].length; j++) {
                if (alignment == Alignment.LEFT) {
                    System.out.print(" " + datum[j]);
                }
                for (int k = 0; k <= columnLengths[j] - datum[j].length(); k++) {
                    System.out.print(" ");
                }
                if (alignment == Alignment.RIGHT) {
                    System.out.print(" " + datum[j]);
                }
                System.out.print("|");
            }
            drawLine('-');
        }
    }
}

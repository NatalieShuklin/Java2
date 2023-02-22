package parser;

import java.io.*;

/**
 * Invoice parse class, parses a file with invoice numbers in ASCII
 */
public class InvoiceParser {

    /*
     * Performs reading the file, one invoice number at a time,
     * reading 4 lines in every iteration. Performing parsing for the number
     * and writing it correctly to the output file.
     * @param inputFile input file
     * @throws IOException files errors
     */
    private static void readFile(File inputFile) throws IOException {
        FileReader reader = new FileReader(inputFile);
        BufferedReader buffered = new BufferedReader(reader);
        File myObj = new File("1EX-out.txt");
        BufferedWriter output = new BufferedWriter(new FileWriter(myObj));

        String line, firstLine, secLine, thirdLine;
        while ((line = buffered.readLine()) != null) {
            firstLine = line;
            secLine = buffered.readLine();
            thirdLine = buffered.readLine();
            buffered.readLine();

            InvoiceNumberParse numParse = new InvoiceNumberParse(firstLine, secLine, thirdLine);
            if (numParse.getErrorInNumber()){
                output.write(numParse.getParsedNumber());
                output.write(" ILLEGAL");
                output.write('\n');
            }
            else{
                output.write(numParse.getParsedNumber()+'\n');
            }
        }
        output.close();
        buffered.close();
    }

    /**
     * main method, receives a file with ASCII inovice numbers in it,
     * and starts performing translation of it into real numbers, in the output
     * file.
     * @param args none given
     * @throws IOException files errors
     */
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input_Q1b .txt");
        readFile(inputFile);
    }
}

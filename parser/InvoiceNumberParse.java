package parser;

/**
 * A class which parses one given ASCII invoice number
 */
public class InvoiceNumberParse {

    private static final int DIGIT_LENGTH = 3;
    private static final int DIGIT_WIDTH = 3;
    private String parsedNumber;
    boolean errorInNumber = false;

    /**
     * constructor,start the process of translating the ASCII number into
     * integer
     * @param firstLine first line of number in ASCII
     * @param secLine sec. line
     * @param thirdLine third line
     */
    public InvoiceNumberParse(String firstLine, String secLine, String thirdLine) {
        processNumber(firstLine,secLine,thirdLine);
    }

    /*
     * Translation process of given ASCII numberin 3 lines into integer
     * @param firstLine firsline
     * @param secLine sec line
     * @param thirdLine third line
     */
    private void processNumber(String firstLine, String secLine, String thirdLine) {
        StringBuilder digit = new StringBuilder();
        StringBuilder strDigit = new StringBuilder();
        int startIndex = 0;
        int endIndex = 3;
        // parse digit by digit
        for ( int index=0;index<9;index++) {
            digit.append(firstLine.substring(startIndex, endIndex));
            digit.append(secLine.substring(startIndex, endIndex));
            digit.append(thirdLine.substring(startIndex, endIndex));
            String parsedDigitNum = parseDigit(digit.toString());
            strDigit.append(parsedDigitNum);

            if (parsedDigitNum.equals("?")){
                errorInNumber = true; //if error digit occurred
            }
            startIndex += 3;
            endIndex += 3;
            digit = new StringBuilder();
        }
        parsedNumber = strDigit.toString();
    }

    /*
     * Parses a single digit in the ASCII invoice number.
     * @param digit given digit in ASCII rep. given as String
     * @return the integer representing number of the digit
     */
    private String parseDigit(String digit) {
        String strDigit="";
        switch (digit) {
            case DigitsConstants.ZERO:
                strDigit= "0";
                break;
            case DigitsConstants.ONE:
                strDigit= "1";
                break;
            case DigitsConstants.TWO:
                strDigit= "2";
                break;
            case DigitsConstants.THREE:
                strDigit= "3";
                break;
            case DigitsConstants.FOUR:
                strDigit= "4";
                break;
            case DigitsConstants.FIVE:
                strDigit= "5";
                break;
            case DigitsConstants.SIX:
                strDigit= "6";
                break;
            case DigitsConstants.SEVEN:
                strDigit= "7";
                break;
            case DigitsConstants.EIGHT:
                strDigit= "8";
                break;
            case DigitsConstants.NINE:
                strDigit= "9";
                break;
            default:
                strDigit="?";
        }
        return strDigit;
    }

    /**
     * Get parsed invoice number
     * @return  invoice number translated from ASCII to int repr.
     */
    public String getParsedNumber() {
        return parsedNumber;
    }

    /**
     * check if error sign read in the given invoice number
     * @return true if error did occur, else false.
     */
    public boolean getErrorInNumber(){
        return errorInNumber;
    }

}

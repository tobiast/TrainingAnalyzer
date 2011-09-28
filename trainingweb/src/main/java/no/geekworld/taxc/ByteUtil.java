package no.geekworld.taxc;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * util class to convert from byte[]
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 11:35:36 PM
 */
public class ByteUtil {

    /**
     * Converts from byte[] to int
     *
     * @param byteToConvert
     * @param startPos
     * @param length
     * @return the int representation of the
     */
    public static int convertToInteger(byte[] byteToConvert, int startPos, int length) {

        Integer value = 0;
        int counter = 0;
        for (int i = startPos; i < startPos + length; i++) {
            value += (byteToConvert[i] & 0xff) << (8 * counter);
            counter++;
        }
        return value;
    }


    /**
     * Converts a byte to a String
     *
     * @param byteToConvert the bytes to convert
     * @param startPos      the start possition in the byte[]
     * @param length        of the string
     * @return string representing the binary ascii
     */
    public static String convertToString(byte[] byteToConvert, int startPos, int length) {

        StringBuffer returnValue = new StringBuffer();

        // each character is two bytes
        for (int y = 0; y < (length / 2); y++) {
            int asciiValue = convertToInteger(byteToConvert, startPos + y * 2, 2);
            // System.out.println("ascii value: " + asciiValue);
            returnValue.append((char) asciiValue);
        }
        return returnValue.toString();
    }


    /**
     * Fix this method - only to check of order was wrong
     *
     * @param data
     * @param pos
     * @return
     */
    public static float bytearray2float(byte[] data, int pos, int length) {
        byte[] dataReversed = new byte[length];
        for (int i = 0; i < length; i++) {
            dataReversed[length - i - 1] = data[pos + i];
        }
        ByteBuffer buf = ByteBuffer.wrap(dataReversed);
        return buf.getFloat();
    }


}

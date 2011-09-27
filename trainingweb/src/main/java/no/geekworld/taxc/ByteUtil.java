package no.geekworld.taxc;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 11:35:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class ByteUtil {

    // converts the byte arrays to an integer value
    public static int convertToInteger(byte[] byteToConvert, int startPos, int length) {

        Integer value = 0;
        int counter = 0;
        for (int i = startPos; i < startPos + length; i++) {
            value += (byteToConvert[i] & 0xff) << (8 * counter);
            counter ++;
        }
        return value;
    }
}

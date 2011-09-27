package no.geekworld.taxc;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 11:13:54 PM
 * To change this template use File | Settings | File Templates.
 */
public enum BlockType {

    GENERAL_INFO(4010), RIDER_INFO(210), RIDE_INFO(4030), RIDE_DATA(4040), COURSE_DATA(4020);

    private int fingerPrint;

    private static final Map<Integer, BlockType> lookup  = new HashMap<Integer, BlockType>();


    static {
        for (BlockType s : EnumSet.allOf(BlockType.class)) {
            lookup.put(s.getFingerPrint(), s);
        }
    }

    BlockType(int fingerPrint) {
        this.fingerPrint = fingerPrint;
    }


    public int getFingerPrint() {
        return fingerPrint;
    }


    public static BlockType get(int code) { 
          return lookup.get(code);
    }

}

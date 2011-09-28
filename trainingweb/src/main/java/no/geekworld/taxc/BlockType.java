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


    NOTES(120),
    LAP_DATA(110),
    UNKNOWN(130),
    RIDER_INFO(210),
    GENERAL_INFO_CAF(1010),
    PROGRAM(1020),
    RLV_VIDEO_INFO(2010),
    RLV_FRAME_DISTANCE_MAPPING(2020),
    RLV_INFOBOX(2030),
    COURSE_INFO(2040),
    RIDE_INFO_CAF(3010),
    RIDE_DATA_CAF(3020),
    GENERAL_INFO(4010),
    RIDE_INFO(4030),
    RIDE_DATA(4040),
    COURSE_DATA(4020);

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

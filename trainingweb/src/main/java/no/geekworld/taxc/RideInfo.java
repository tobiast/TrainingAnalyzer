package no.geekworld.taxc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 28, 2011
 * Time: 8:36:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RideInfo {


    String courseName;
    Date rideDate;
    double distance;
    double duration;
    float recordInterval;

    public RideInfo(byte[] data, int startPos) throws ParseException {

        int day = ByteUtil.convertToInteger(data, startPos+6, 2);
        int month = ByteUtil.convertToInteger(data, startPos+2,2);
        int year = ByteUtil.convertToInteger(data, startPos, 2);
        int hour = ByteUtil.convertToInteger(data, startPos+8, 2);
        int minute = ByteUtil.convertToInteger(data, startPos+10, 2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-yyyy hh:mm");
        rideDate = sdf.parse("" + day+"/" +month+"-" +year+ " "+ hour+":"+minute);

        distance  = ByteUtil.bytearray2float(data, startPos+20, 8);
        duration  = ByteUtil.bytearray2float(data, startPos+28, 8);
        recordInterval = ByteUtil.bytearray2float(data, startPos +16, 4 );
        courseName = ByteUtil.convertToString(data,startPos+202, 34);

    }

    @Override
    public String toString() {
        return "RideInfo{" +
                "courseName='" + courseName + '\'' +
                ", rideDate=" + rideDate +
                ", distance=" + distance +
                ", duration=" + duration +
                ", recordInterval=" + recordInterval +
                '}';
    }
}

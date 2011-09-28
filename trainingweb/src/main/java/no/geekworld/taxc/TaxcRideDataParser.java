package no.geekworld.taxc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 28, 2011
 * Time: 5:39:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaxcRideDataParser {



    List<RideData> rideData = new ArrayList<RideData>(); 

    public List<RideData> parse(byte[] data, int startPos, int numberOfBlocks){

        int currentStartPos = startPos;
        for (int i = 0; i < numberOfBlocks; i++) {
            rideData.add(new RideData(data, currentStartPos));
            currentStartPos+=10;
        }


        for (int i = 0; i < rideData.size(); i++) {
            RideData rideData1 = rideData.get(i);
            System.out.println(rideData1.toString());
        }

          return rideData;

    }


}

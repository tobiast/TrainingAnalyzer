package no.geekworld.taxc;

public class RideData {
    private float distance;
    private int heartRate;
    private int cadence;
    private int powerX10;
    private int speedX10;

    public RideData() {
    }


    public RideData(byte[] bytes, int startPos) {

        distance = ByteUtil.bytearray2float(bytes, startPos,4);
        heartRate = ByteUtil.convertToInteger(bytes, startPos + 4, 1);
        cadence = ByteUtil.convertToInteger(bytes, startPos + 5, 1);
        powerX10 = ByteUtil.convertToInteger(bytes, startPos + 6, 2);
        speedX10 = ByteUtil.convertToInteger(bytes, startPos + 8, 2);
    }


    @Override
    public String toString() {
        return "TaxcRideDataParser{" +
                "distance=" + distance +
                ", heartRate=" + heartRate+
                ", cadence=" + cadence +
                ", powerX10=" + powerX10 +
                ", speedX10=" + speedX10 +
                '}';
    }
}
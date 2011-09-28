package no.geekworld.taxc;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles parsing of a taxc file
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 3:26:30 PM
 */
public class TaxcParser {
    private String fileName;


    public TaxcParser(String fileName) {
        this.fileName = fileName;
    }

    public void parse() throws IOException, ParseException {

        File file = new File(fileName);
        FileInputStream fstream = new FileInputStream(file);

        byte[] bytes = new byte[(int) file.length()];
        int readResult = fstream.read(bytes, 0, (int) file.length());

        Map<BlockType, BlockInfo> blocks = getBlocksInFile(bytes);

        BlockInfo rider = blocks.get(BlockType.RIDER_INFO);

        rider.getStartPos();
        System.out.println("Club name:  " + ByteUtil.convertToString(bytes, rider.getDataStartPos(), 34));
        System.out.println("Rider name:  " + ByteUtil.convertToString(bytes, (rider.getDataStartPos()+ 34), 34));


        BlockInfo ride_info = blocks.get(BlockType.RIDE_INFO_CAF);
        RideInfo rideInfo = new RideInfo(bytes, ride_info.getDataStartPos());
        System.out.println("RideInfo" + rideInfo.toString());


        BlockInfo ride_data = blocks.get(BlockType.RIDE_DATA_CAF);

        TaxcRideDataParser dataparser = new TaxcRideDataParser();
        dataparser.parse(bytes, ride_data.getDataStartPos() , ride_data.getNumberOfRecords());

        System.out.println(dataparser.toString());




        fstream.close();
    }


    /**
     * Parses the file and returns a map containing block infos, eg. what type of info that is contained in the file
     * contained in the blocks. For more info check: http://www.whitepeak.org/HeaderBlock.aspx and
     * http://www.whitepeak.org/InfoBlock.aspx
     *
     * @param file a Taxc file containing binary data
     * @return a map containing block info on all the blocks
     */
    private Map<BlockType, BlockInfo> getBlocksInFile(byte[] file) {

        Map<BlockType, BlockInfo> blocks = new HashMap<BlockType, BlockInfo>();
        //Header info

        int number_of_blocks = ByteUtil.convertToInteger(file, 4, 4);
        System.out.println("number of blocks in file: " + number_of_blocks);


        int lastIndex = 8;
        for (int i = 0; i < number_of_blocks; i++) {
            BlockInfo block = new BlockInfo(file, lastIndex);
            System.out.println("Blockinfo:" + block.toString());
            lastIndex = block.getLastIndexForBlock();

            blocks.put(block.getBlockType(), block);
        }

        return blocks;
    }


}



   









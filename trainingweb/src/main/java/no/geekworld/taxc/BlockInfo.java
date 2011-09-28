package no.geekworld.taxc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 11:22:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlockInfo {

    private BlockType blockType;
    private int numberOfRecords;
    private int blockVersion;
    private int recordSize;
    private int startPos;
    private static final int BLOCK_INFO_SIZE = 12;
    

    /**
     * Create a block info object from the bytes containing the block info
     * @param bytes the bytes containing the block info
     */
    public BlockInfo(byte[] bytes, int startPos) {
        this.blockType = BlockType.get(ByteUtil.convertToInteger(bytes, startPos, 2));
        this.blockVersion = ByteUtil.convertToInteger(bytes, startPos+2, 2);
        this.numberOfRecords = ByteUtil.convertToInteger(bytes, startPos+4, 4);
        this.recordSize = ByteUtil.convertToInteger(bytes, startPos+8, 4);;
        this.startPos = startPos;
    }


    /**
     *
     * @return the last index of the block
     */
    public int getLastIndexForBlock(){
        return startPos + BLOCK_INFO_SIZE + (numberOfRecords * recordSize);
    }

    /**
     *
     * @return the type of block
     */
    public BlockType getBlockType() {
        return blockType;
    }

    /**
     *
     * @return  the startpos of the block in the binary file. The header info is included
     */
    public int getStartPos() {
        return startPos;
    }


    /**
     *
     * @return  the startpos of the block in the binary file. No header info is included
     */
    public int getDataStartPos() {
        return startPos + BLOCK_INFO_SIZE;
    }


    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     *
     * @param theFile
     * @return
     */
    private List<byte[]> getRecords(byte[] theFile){

        List<byte[]> records = new ArrayList<byte[]>();
        for (int i = startPos; i< startPos + recordSize; i++){
            records.add(Arrays.copyOfRange(theFile,startPos + i*recordSize, recordSize));
        }
        return records;
            
    }

    @Override
    public String toString() {
        return "BlockInfo{" +
                "blockType=" + blockType +
                ", blockVersion=" + blockVersion +
                ", numberOfRecords=" + numberOfRecords +
                ", recordSize=" + recordSize +
                ", startPos=" + startPos +
                '}';
    }
}

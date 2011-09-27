package no.geekworld.taxc;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 27, 2011
 * Time: 3:26:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaxcParser {
    private String fileName;


    public TaxcParser(String fileName) {
        this.fileName = fileName;
    }

    public void parse() throws IOException {


        File file = new File(fileName);
        FileInputStream fstream = new FileInputStream(file);


        byte[] bytes = new byte[(int)file.length()];
        fstream.read(bytes, 0, (int)file.length());


        // first block fingerprint
        System.out.println("byte 112");
        System.out.println("Value: " + ByteUtil.convertToInteger(bytes, 124, 2));



        BlockInfo firstBlock = new BlockInfo(bytes, 8);
        System.out.println("Blockinfo:" +  firstBlock.toString());

        System.out.println("Last index for first block: " + firstBlock.getLastIndexForBlock());

        BlockInfo secondBlock = new BlockInfo(bytes, firstBlock.getLastIndexForBlock());
        System.out.println("Blockinfo:" +  secondBlock.toString());

        BlockInfo thirdBlock = new BlockInfo(bytes, secondBlock.getLastIndexForBlock());
        System.out.println("Blockinfo:" +  thirdBlock.toString());

        BlockInfo fourthBlock = new BlockInfo(bytes, thirdBlock.getLastIndexForBlock());
        System.out.println("Blockinfo:" +  fourthBlock.toString());


        for (int i = 0; i < bytes.length; i++) {
            byte aByte = bytes[i];
            System.out.println("byte " + i + " value:" + bytes[i]);
        }


        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;


        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            System.out.println(strLine);
        }
        //Close the input stream
        in.close();
    }



   

}







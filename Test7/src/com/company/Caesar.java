package com.company;

import java.io.*;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public class Caesar extends Encryption {

    @Override
    public void decrypt(int key, File file, File decryptedFile) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(decryptedFile);
            int actuallyRead = 0;
            byte currentByte;
            while ((actuallyRead = inputStream.read()) != -1) {
                currentByte = doDecrypt(actuallyRead, key);
                outputStream.write(currentByte);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    private byte doDecrypt(int actuallyRead, int key) {
        return (byte) (actuallyRead - key);
    }

}
package com.company;

import java.io.File;

/**
 * Created by Sapir on 29.03.2017.
 */
public class UploadedFile extends File  {

    private int version;
    private byte[] fileNameBytes;
    private boolean lock;// אם מישהו מעלה או מוריד ברגע זה


    public UploadedFile(String path){
        super(path);
        this.version = 0;
    }

    public byte[] getFileNameBytes() {
        return fileNameBytes;
    }

    public void setFileNameBytes(byte[] fileNameBytes) {
        this.fileNameBytes = fileNameBytes;
    }

    public void increaseVersion(){
        version++;
    }

    public void lock(){
        lock = true;
    }

    public void unlock(){
        lock = false;
    }

    public boolean isLocked(){
        return lock;
    }
}

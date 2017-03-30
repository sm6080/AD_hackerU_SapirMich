package com.company;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sapir on 29.03.2017.
 */
public class UploadedFile extends File  {



    private int version;
    private byte[] fileNameBytes;
    private boolean lock;// אם מישהו מעלה או מוריד ברגע זה
    public AtomicInteger concurrentDownload;

    public UploadedFile(String path){
        super(path);
        this.version = 0;
        this.concurrentDownload=new AtomicInteger(0);
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

    public int getVersion() {
        return version;
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

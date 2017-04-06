package com.company;

import java.io.File;

/**
 * Created by Sapir Michaeli on 16.03.2017.
 */
public abstract class Encryption<T> {


    public abstract void decrypt(int key, File file, File decryptedFile);


}

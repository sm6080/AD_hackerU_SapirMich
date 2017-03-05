package com.company;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;
import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:/Users/hackeru.HACKERU3/Documents/sapir_m/files/");
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        File[] files = file.listFiles();  // listFiles - כל הקבצים שנמצאים בתיקיות מתחתיי
        for (File f : files) {
            System.out.println(f.getAbsolutePath());

        }

        File file1 = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\sapir_m\\files\\");
      //  System.out.println(existFile(file1, "sap.txt"));

        File file2 = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\sapir_m\\files\\sap.txt");
        System.out.println(counLines(file2)+"sss");
    }

    /*
   כתבי פונקציה שמקבלת קובץ שהוא תיקיה , אחרת אקספשיין. עוד פרמטר לפונקציה , שם של קובץ
  הפונקציה מחזירה אמת או שקר האם הקובץ קיים בתיקייה. יכול להיות שקיים בתיקייה פנימית כלשהיא */
    public static boolean existFile(File file, String name) {
            if(!file.exists())
                throw new InvalidParameterException();
            if(!file.isDirectory())
                throw new InvalidParameterException();
            File [] files=file.listFiles();
            for (File f:files) {
                if(f.isFile()&&f.getName().equals(name))
                    return true;
            }
            for (File f:files) {
                if(f.isDirectory()&&existFile(f,name))
                    return true;
            }
            return false;
        }

    //פונקציה שמקבלת נתיב לתיקיה ומחזירה את הקובץ הגדול ביותר
    public static File getLargestFile ( File file) throws Exception {
        if (!file.exists())
            throw new Exception("not exist");
        long maxSize = -1L;
        File largestFile = null;
        File[] files = file.listFiles();
        for (File f : files) {
            long size;
            File currentFile = null;
            if (f.isHidden())
                continue;
            if (f.isFile()) {
                size = f.length();
                currentFile = f;
            } else {
                currentFile = getLargestFile(f);
                if (currentFile != null)
                    size = currentFile.length();
                else
                    size = -1L;
            }
            if (size > maxSize) {
                maxSize = size;
                largestFile = currentFile;
            }
        }
        return largestFile;
    }





    public static void fileChooser() throws Exception {
        File file = new File("/Users/");
        if (!file.exists())
            throw new Exception("is not exist");
        if (!file.isDirectory())
            throw new Exception("is not a directory");
        while (true) {
            System.out.println("please choose file within" + file.getAbsolutePath());
            System.out.println("enter 0 to go to parent directory");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + " " + files[i].getName());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferedReader.readLine();
            int option = 0;
            try {
                option = Integer.valueOf(line);
            } catch (Exception ex) {
                System.out.println("must enter a number");
                continue;
            }
            if (option < 0 || option > files.length)
                throw new Exception("invalid option");
            if (option == 0) {
                if (file.getParentFile() != null)
                    file = file.getParentFile();
                continue;
            }
            File f = files[option - 1];
            if (f.isFile()) {
                System.out.println("you have chosen the file:");
                System.out.println(f.getAbsolutePath());
                break;
            } else {
                file = f;
            }
        }
    }







        // counts numbers of lines in file
    public static int counLines(File file) {
        InputStream inputStream = null;
        int actuallyRead;
        int count=0;
        boolean flag=false;
        try {
            inputStream = new FileInputStream(file);
            while ((actuallyRead = inputStream.read()) != -1)
                if(actuallyRead=='\n') {
                    count++;
                    flag=false;
                }
                else
                    flag=true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (flag)
             count++;
        return count;
    }

}

package com.hisham.javalibrary.iostreams;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

/**
 * Created by Hisham on 19/Oct/2018 - 21:05
 */
public class IODriver {

    private static String text;

    public static void main(String args[]){

        text = "h";
        String textSmall = "SomeText";
        MyInputStream inputStream = new MyInputStream(text);
        byte[] buffer = new byte[4096];

        char[] chars = new char[textSmall.length()];
        textSmall.getChars(0, chars.length, chars, 0);
        byte[] encode = StringCoding.encode(chars, 0, textSmall.length());

        System.out.println(new String(encode));

        char[] decode = StringCoding.decode(encode, 0, encode.length);
        System.out.println(new String(decode));

        long startTime = System.currentTimeMillis();
        try {
            System.out.println("Hashcode: " + inputStream.hashCode());

            int totalBytesRead = inputStream.read(buffer);
            System.out.println("Bytes read: " + totalBytesRead);

//            long skipped = inputStream.skip(2040009);
//            System.out.println("Bytes skipped: " + skipped);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Time taken with buffer = " + (System.currentTimeMillis() - startTime) + " ms");


        readOneAtATime();
    }

    private static void readOneAtATime() {
        long startTime = System.currentTimeMillis();
        try {
            MyInputStream inputStream = new MyInputStream(text);
            StringBuilder builder = new StringBuilder();
            int i;
            while ( (i = inputStream.read()) != -1){
                builder.append((char)i);
            }
//            System.out.println("Bytes read: " + builder.toString());

//            long skipped = inputStream.skip(2040009);
//            System.out.println("Bytes skipped: " + skipped);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("readOneAtATime: Time taken = " + (System.currentTimeMillis() - startTime) + " ms");
    }

}

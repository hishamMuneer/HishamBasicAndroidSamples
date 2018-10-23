package com.hisham.javalibrary.iostreams;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hisham on 19/Oct/2018 - 21:06
 */
public class MyInputStream extends InputStream {

    private final String text;
    private int currentPosition = 0;

    public MyInputStream(String text) {
        this.text = text;
    }

    @Override
    public int read() throws IOException {

        if(currentPosition > -1 && currentPosition < text.length()){
          return (int) text.charAt(currentPosition++);
        }

        return -1;
    }
}

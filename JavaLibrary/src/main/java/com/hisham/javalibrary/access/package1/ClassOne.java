package com.hisham.javalibrary.access.package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Created by Hisham on 29/Oct/2018 - 17:16
 */
 public class ClassOne {

    /*package*/ int data; // Variable accessible only within package.
    protected int data2; // Variable accessible to childs + package


    void fun(){
        try{

            FileWriter writer = new FileWriter("");
            writer.write("");
            File file = new File("");
        }catch(Throwable e){
            System.out.print(e);
        }
    }
}

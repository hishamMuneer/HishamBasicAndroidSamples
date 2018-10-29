package com.hisham.javalibrary.access.package2;

import com.hisham.javalibrary.access.package1.ClassOne;

/**
 * Created by Hisham on 29/Oct/2018 - 17:16
 */
public class ClassTwo extends ClassOne {

    void foo(){
        data2 = 1; // from ClassOne
        ClassOne one = new ClassOne();
//        one.data2 = 10;
    }

}

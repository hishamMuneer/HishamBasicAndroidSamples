package com.hisham.javalibrary.hashcode_equals_hashcollections;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hisham on 29/Oct/2018 - 01:42
 */
public class PojoVerifierMain {

    public static void main(String... args){

        PojoObject obj1 = new PojoObject(1, "Hisham");
        PojoObject obj2 = new PojoObject(1, "Hisham");

        Map<PojoObject, String> map = new HashMap<>();
        map.put(obj1, "Data");
        map.put(obj2, "Data");

        System.out.println("Map Size should be One - Found: " + map.size());
        boolean hashEquals = obj1.hashCode() == obj2.hashCode();
        System.out.println("Is Hash of both object equals: " + hashEquals);


    }

}

package com.hisham.javalibrary.hashcode_equals_hashcollections;

/**
 * Created by Hisham on 29/Oct/2018 - 01:37
 */
public class PojoObject {

    private int importantField;
    private String otherField;

    public PojoObject(int importantField, String otherField) {
        this.importantField = importantField;
        this.otherField = otherField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PojoObject that = (PojoObject) o;

        if (importantField != that.importantField) return false;
        return true;//otherField != null ? otherField.equals(that.otherField) : that.otherField == null;
    }

//    @Override
//    public int hashCode() {
//        return importantField;
//    }
}

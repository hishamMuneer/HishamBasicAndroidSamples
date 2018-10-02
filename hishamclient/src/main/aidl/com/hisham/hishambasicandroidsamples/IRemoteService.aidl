// IRemoteService.aidl.aidl
package com.hisham.hishambasicandroidsamples;

// Declare any non-default types here with import statements

interface IRemoteService {

    int getPid();
    long getThreadId();
    String getThreadName();
    int getVariable();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, String aString);
}
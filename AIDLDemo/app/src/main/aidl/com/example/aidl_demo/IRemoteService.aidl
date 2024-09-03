// IRemoteService.aidl
package com.example.aidl_demo;

// Declare any non-default types here with import statements

interface IRemoteService {
    int getPid();

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
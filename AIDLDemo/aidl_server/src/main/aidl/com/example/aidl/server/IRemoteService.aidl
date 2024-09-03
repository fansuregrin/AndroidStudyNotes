// IRemoteService.aidl
package com.example.aidl.server;

// Declare any non-default types here with import statements

interface IRemoteService {
    int getPid();

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
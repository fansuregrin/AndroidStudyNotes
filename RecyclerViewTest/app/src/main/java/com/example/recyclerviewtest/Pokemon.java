package com.example.recyclerviewtest;

public class Pokemon {
    private final int mNameId;
    private final int mImageId;
    private final int mDescId;

    public Pokemon(int nameId, int imageId, int descId) {
        mNameId = nameId;
        mImageId = imageId;
        mDescId = descId;
    }

    public int getNameId() {
        return mNameId;
    }

    public int getImageId() {
        return mImageId;
    }

    public int getDescId() {
        return mDescId;
    }
}

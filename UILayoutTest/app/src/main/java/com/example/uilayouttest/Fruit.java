package com.example.uilayouttest;

public class Fruit {
    private final int nameId;
    private final int imageId;

    public Fruit(int nameId, int imageId) {
        this.nameId = nameId;
        this.imageId = imageId;
    }

    public int getNameId() {
        return nameId;
    }

    public int getImageId() {
        return imageId;
    }
}

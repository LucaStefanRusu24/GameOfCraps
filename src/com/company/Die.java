package com.company;

import java.util.Random;

public class Die {
    private int faceValue;
    private int n;
    Random rand = new Random();

    Die(int n){
        this.n = n;
    }

    public int getFaceValue(){
        return faceValue;
    }

    public void setNoFaces(int n){
        this.n = n;
    }

    public void roll(){
        faceValue = rand.nextInt(n) + 1;
    }
}

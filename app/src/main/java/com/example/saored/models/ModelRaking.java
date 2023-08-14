package com.example.saored.models;

//Create a custom class for ranking items
//implements Comparable<ModelRaking>
public class ModelRaking  {
    private String lop; //the name of the item
    private double scores; //the score of the item

    public ModelRaking() {
    }

    //Constructor
    public ModelRaking(String lop, double scores, String uid) {
        this.lop = lop;
        this.scores = scores;

    }

    //Getter methods
    public String getLop() {
        return lop;
    }

    public double getScores() {
        return scores;
    }

}

package com.example.demo.models.database;

public class Test {
    protected int testID;

    public String getText() {
        return text;
    }

    public int getTestID() {
        return testID;
    }

    public String getCorrect() {
        return correct;
    }

    public String getWrong1() {
        return wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    protected String text;
    protected String correct;
    protected String wrong1;
    protected String wrong2;

    protected Test(String text, String correct, String wrong1, String wrong2) {
        this.text = text;
        this.correct = correct;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    public Test() {

    }




}

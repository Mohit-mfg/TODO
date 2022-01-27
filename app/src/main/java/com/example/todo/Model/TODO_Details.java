package com.example.todo.Model;

public class TODO_Details {

    private String title;
    private String discription;
    private String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TODO_Details(String title, String discription, String date) {
        this.title = title;
        this.discription = discription;
        this.date = date;
    }

}

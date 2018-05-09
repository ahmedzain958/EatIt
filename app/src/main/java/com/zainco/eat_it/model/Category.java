package com.zainco.eat_it.model;

public class Category {
    private String Image;
    private String Name;


    public Category() {
    }

    public Category(String image, String name) {
        this.Image = image;
        this.Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

package com.hank.domain.item;

public abstract class Combo implements Cloneable{

    protected int id;
    protected int price;
    protected String name;
    protected String category;
    protected int bought;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    @Override
    public Combo clone() throws CloneNotSupportedException {
       Combo combo = (Combo)super.clone();
       return combo;
    }
    
    
    
}

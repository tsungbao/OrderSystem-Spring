package com.hank.domain.item;

import java.io.Serializable;

public abstract class Combo implements  Cloneable , Serializable{

    protected int id;
    protected int price;
    protected String name;
    protected String category;
    protected int bought;
    protected int cartId;
    protected String imgResc;

    public String getImgResc() {
        return imgResc;
    }

    public void setImgResc(String imgResc) {
        this.imgResc = imgResc;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

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

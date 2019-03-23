package com.hank.domain.item;

public abstract class CondimentDecorator extends Combo {

    protected Combo combo;

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Combo getCombo() {
        return combo;
    }

    @Override
    public int getPrice() {
        if (combo!=null) {
            price += combo.getPrice();
            this.setPrice(price);
        }
        return price;
    }

    @Override
    public CondimentDecorator clone() throws CloneNotSupportedException {
        CondimentDecorator replication = (CondimentDecorator) super.clone();
        if (combo != null) {
            replication.combo = this.combo.clone();
        }

        return replication;
    }

}


package com.hank.domain.item;

import java.util.ArrayList;


public class RealCombo extends Combo    {
     private ArrayList<String> description ; 
     private CondimentDecorator content;

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public CondimentDecorator getContent() {
        return content;
    }

    public void setContent(CondimentDecorator content) {
        this.content = content;
    }

 
    
}

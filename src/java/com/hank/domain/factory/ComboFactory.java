package com.hank.domain.factory;

import com.hank.domain.item.Combo;
import com.hank.domain.item.CondimentDecorator;
import com.hank.domain.item.RealCombo;
import java.util.ArrayList;
import java.util.ListIterator;

public class ComboFactory implements ItemFactory {


    public RealCombo createCombo(int id, String name, String category, String description, int bought, Combo[] contents) {
        RealCombo realCombo = new RealCombo();

        realCombo.setId(id);
        realCombo.setName(name);
        realCombo.setCategory(category);
        realCombo.setBought(bought);

        // 如今我把RealCombo裡的  description (String [])  的資料結構改成 ArrayList<String>
        String[] description_Array = description.split(",");
        ArrayList<String> desc = new ArrayList<>();
        for (String content : description_Array) {
            desc.add(content);
        }
        realCombo.setDescription(desc);
        //

        // setContent 
        ArrayList<Combo> contents_new_order = new ArrayList<>();

        A1:
        for (Combo content : contents) {
            try {
                if (content.getCategory().equals("主餐")) {
                    contents_new_order.add(0, content);
                } else {
                    contents_new_order.add(content);
                }
            } catch (NullPointerException e) {
                break A1;
            }

        }
        // 指到 contents_new_order 最後一個元素 的後面一個

        ListIterator<Combo> it = contents_new_order.listIterator(contents_new_order.size());
        while (it.hasPrevious()) {
            Combo content_subsequent = it.previous();
            if (it.hasPrevious()) {
                Combo content_previous = it.previous();
                if (content_subsequent instanceof CondimentDecorator) {
                    ((CondimentDecorator) content_subsequent).setCombo(content_previous);
                }
                it.next();
            }
        }

        CondimentDecorator the_one_in_realCombo_content = (CondimentDecorator) contents_new_order.get(contents_new_order.size() - 1);

        realCombo.setContent(the_one_in_realCombo_content);
        realCombo.setPrice(the_one_in_realCombo_content.getPrice() - 5);
        //
        return realCombo;
    }

    public static void fromString_toList(String desc , ArrayList<String> desc_list){
        String[] desc_array = desc.split(",");
        for(String content : desc_array){
            desc_list.add(content);
        }
    }
}

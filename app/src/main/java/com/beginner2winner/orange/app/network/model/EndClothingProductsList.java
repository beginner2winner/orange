package com.beginner2winner.orange.app.network.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class EndClothingProductsList {

    private List<ProductItem> items = new ArrayList<>();

    public void add(ProductItem item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public ProductItem getItem(int pos) {
        return items.get(pos);
    }

    public ArrayList<ProductItem> getItemsCopy() {
        return new ArrayList<>(this.items);
    }

    public void copyFrom(EndClothingProductsList other) {
        this.items = other.getItemsCopy();
    }
}

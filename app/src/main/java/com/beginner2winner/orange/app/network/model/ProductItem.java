package com.beginner2winner.orange.app.network.model;

import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductItem {

    private String id;
    private Map<String, ItemAttribute> attributes = new HashMap<>();

    public ProductItem(String id) {
        this.id = id;
    }

    public void addAttribute(ItemAttribute item) {
        this.attributes.put(item.getName(), item);
    }

    public Map<String, ItemAttribute> getAttributesCopy() {
        return new HashMap<>(this.attributes);
    }

    public ItemAttribute getAttribute(String name) {
        return this.attributes.get(name);
    }

    public Set<String> getAttributeNames() {
        return new HashSet<>(this.attributes.keySet());
    }

    // Interpretations

    @Nullable
    public ItemAttribute getThumbnailURL() {
        return getAttribute("_thumburl");
    }

    @Nullable
    public ItemAttribute getPrice() {
        return getAttribute("price_gbp");
    }

    @Nullable
    public ItemAttribute getName() {
        return getAttribute("name");
    }
}

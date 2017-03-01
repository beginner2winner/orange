package com.beginner2winner.orange.activities.products.ui;

import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsItemViewHolderBigFactory implements ProductsItemViewHolderFactory {

    @Inject
    public ProductsItemViewHolderBigFactory() {
    }

    @Override
    public ProductsItemViewHolder createViewHolder(Picasso picasso, ProductsItemFormatter productsItemFormatter, ViewGroup parent) {
        return new ProductsItemViewHolderBig(picasso, productsItemFormatter, parent);
    }
}

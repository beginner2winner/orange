package com.beginner2winner.orange.activities.products.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.beginner2winner.orange.activities.products.ui.ProductsItemFormatter;
import com.beginner2winner.orange.activities.products.ui.ProductsItemViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public interface ProductsItemViewHolderFactory {
    ProductsItemViewHolder createViewHolder(Picasso picasso, ProductsItemFormatter productsItemFormatter, ViewGroup parent);
}

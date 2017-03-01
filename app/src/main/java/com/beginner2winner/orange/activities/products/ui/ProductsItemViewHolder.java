package com.beginner2winner.orange.activities.products.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.beginner2winner.orange.app.network.model.ProductItem;
import com.squareup.picasso.Picasso;

/**
 * Recycler Base View Holder for Product Item
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public abstract class ProductsItemViewHolder extends RecyclerView.ViewHolder {

    public static final int SMALL = 0;
    public static final int BIG = 1;

    protected final Picasso picasso;
    protected final ProductsItemFormatter productsItemFormatter;

    public ProductsItemViewHolder(Picasso picasso, ProductsItemFormatter productsItemFormatter, View itemView) {
        super(itemView);
        this.picasso = picasso;
        this.productsItemFormatter = productsItemFormatter;
    }

    abstract public void bind(ProductItem productItem);
}

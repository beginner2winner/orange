package com.beginner2winner.orange.activities.products.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.beginner2winner.orange.app.network.model.EndClothingProductsList;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * Recycler Adapter for Products
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsItemViewHolder> {

    private final Picasso picasso;
    private final ProductsItemFormatter formatter;
    private final Map<Integer, ProductsItemViewHolderFactory> viewHolderFactories;
    private EndClothingProductsList endClothingProductsList = new EndClothingProductsList();

    public ProductsAdapter(Picasso picasso, ProductsItemFormatter formatter, Map<Integer, ProductsItemViewHolderFactory> viewHolderFactories) {
        this.picasso = picasso;
        this.formatter = formatter;
        this.viewHolderFactories = viewHolderFactories;
    }

    @Override
    public ProductsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return this.viewHolderFactories.get(viewType).createViewHolder(this.picasso, this.formatter, parent);
    }

    @Override
    public void onBindViewHolder(ProductsItemViewHolder holder, int position) {
        holder.bind(this.endClothingProductsList.getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (this.endClothingProductsList.getItem(position).getThumbnailURL() == null) {
            return ProductsItemViewHolder.SMALL;
        } else {
            return ProductsItemViewHolder.BIG;
        }
    }

    @Override
    public int getItemCount() {
        if (endClothingProductsList == null) {
            return 0;
        } else {
            return endClothingProductsList.getItemCount();
        }
    }

    public void setData(EndClothingProductsList endClothingProductsList) {
        this.endClothingProductsList.copyFrom(endClothingProductsList);
        notifyDataSetChanged();
    }
}

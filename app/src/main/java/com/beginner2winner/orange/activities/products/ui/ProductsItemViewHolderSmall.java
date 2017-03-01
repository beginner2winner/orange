package com.beginner2winner.orange.activities.products.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beginner2winner.orange.R;
import com.beginner2winner.orange.app.network.model.ProductItem;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recycler View Holder for Product Item shown in small format
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsItemViewHolderSmall extends ProductsItemViewHolder {

    @BindView(R.id.product_item_name)
    TextView productItemNameTextView;

    @BindView(R.id.product_item_price)
    TextView productItemPriceTextView;

    public ProductsItemViewHolderSmall(Picasso picasso, ProductsItemFormatter productsItemFormatter, ViewGroup parent) {
        super(picasso,
                productsItemFormatter,
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_listed_product_small, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(ProductItem productItem) {
        productItemNameTextView.setText(this.productsItemFormatter.getName(productItem));
        productItemPriceTextView.setText(this.productsItemFormatter.getPrice(productItem));
    }

}

package com.beginner2winner.orange.activities.products.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beginner2winner.orange.R;
import com.beginner2winner.orange.app.network.model.ProductItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Recycler View Holder for Product Item shown in big format
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsItemViewHolderBig extends ProductsItemViewHolder {

    @BindView(R.id.product_item_image)
    ImageView productItemThumbnail;

    @BindView(R.id.product_item_name)
    TextView productItemNameTextView;

    @BindView(R.id.product_item_price)
    TextView productItemPriceTextView;

    public ProductsItemViewHolderBig(Picasso picasso, ProductsItemFormatter productsItemFormatter, ViewGroup parent) {
        super(picasso,
                productsItemFormatter,
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_listed_product_big, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(ProductItem productItem) {
        String thumbnailURL = this.productsItemFormatter.getThumbnailURL(productItem);
        if (thumbnailURL != null) {
            this.picasso
                    .load(thumbnailURL)
                    .into(productItemThumbnail);
        }

        productItemNameTextView.setText(this.productsItemFormatter.getName(productItem));
        productItemPriceTextView.setText(this.productsItemFormatter.getPrice(productItem));
    }

}

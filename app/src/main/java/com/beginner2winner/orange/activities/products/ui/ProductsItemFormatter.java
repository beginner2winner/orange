package com.beginner2winner.orange.activities.products.ui;

import com.beginner2winner.orange.app.network.model.ProductItem;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public interface ProductsItemFormatter {

    String getPrice(ProductItem productItem);
    String getThumbnailURL(ProductItem productItem);
    String getName(ProductItem productItem);
}

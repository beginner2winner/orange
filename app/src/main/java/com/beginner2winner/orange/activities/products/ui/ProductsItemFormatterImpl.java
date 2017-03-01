package com.beginner2winner.orange.activities.products.ui;

import android.content.Context;

import com.beginner2winner.orange.R;
import com.beginner2winner.orange.app.network.model.ItemAttribute;
import com.beginner2winner.orange.app.network.model.ProductItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Formatter for Product Item to determine how to display raw values or how to pass them to other
 * UI features such as Picasso. A visualization adapter.
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsItemFormatterImpl implements ProductsItemFormatter {

    private final Context context;

    public ProductsItemFormatterImpl(Context context) {
        this.context = context;
    }

    // Interface ProductsItemFormatter

    @Override
    public String getPrice(ProductItem productItem) {
        ItemAttribute attr = productItem.getPrice();
        if (attr != null && attr.toString().length() > 0) {
            try {
                BigDecimal decimal = new BigDecimal(attr.toString());
                DecimalFormat format = new DecimalFormat(context.getString(R.string.price_number_format));
                String decimalStr = format.format(decimal);
                return String.format(context.getString(R.string.price_number), "£", decimalStr);
            } catch (NumberFormatException ex) {
                return "";
            }
        } else {
            return "";
        }
    }

    @Override
    public String getThumbnailURL(ProductItem productItem) {
        return plainStringIfExistsOtherwiseBlank(productItem.getThumbnailURL());
    }

    @Override
    public String getName(ProductItem productItem) {
        return plainStringIfExistsOtherwiseBlank(productItem.getName());
    }

    private String plainStringIfExistsOtherwiseBlank(ItemAttribute attr) {
        if (attr != null) {
            return attr.toString();
        } else {
            return "";
        }
    }

    private String plainStringIfExistsOtherwiseBlank(ProductItem productItem, String attrName) {
        return plainStringIfExistsOtherwiseBlank(productItem.getAttribute(attrName));
    }
}

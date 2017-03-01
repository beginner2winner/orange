package com.beginner2winner.orange.activities.products.dagger;

import com.beginner2winner.orange.activities.products.ProductsActivity;
import com.beginner2winner.orange.activities.products.mvp.ProductsModel;
import com.beginner2winner.orange.activities.products.mvp.ProductsPresenter;
import com.beginner2winner.orange.activities.products.mvp.ProductsView;
import com.beginner2winner.orange.activities.products.ui.ProductsAdapter;
import com.beginner2winner.orange.activities.products.ui.ProductsItemFormatter;
import com.beginner2winner.orange.activities.products.ui.ProductsItemFormatterImpl;
import com.beginner2winner.orange.activities.products.ui.ProductsItemViewHolder;
import com.beginner2winner.orange.activities.products.ui.ProductsItemViewHolderBigFactory;
import com.beginner2winner.orange.activities.products.ui.ProductsItemViewHolderFactory;
import com.beginner2winner.orange.activities.products.ui.ProductsItemViewHolderSmallFactory;
import com.beginner2winner.orange.app.network.EndClothingNetwork;
import com.squareup.picasso.Picasso;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;

/**
 * Dagger2 Module for Products
 *
 * Includes a demonstration of what is termed Assisted Injection: the view holders' constructor
 * parameter of a ViewGroup is only known at runtime, but we still want to use Dagger for view holders
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@Module
public class ProductsModule {

    private final ProductsActivity activity;

    public ProductsModule(ProductsActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ProductsScope
    public ProductsView view(Picasso picasso) {
        return new ProductsView(activity, picasso);
    }

    @Provides
    @ProductsScope
    public ProductsModel model(EndClothingNetwork endClothingNetwork) {
        return new ProductsModel(activity, endClothingNetwork);
    }

    @Provides
    @ProductsScope
    public ProductsPresenter presenter(ProductsView productsView, ProductsModel productsModel) {
        return new ProductsPresenter(productsView, productsModel);
    }

    @Provides
    @ProductsScope
    public ProductsAdapter adapter(Picasso picasso, ProductsItemFormatter formatter, Map<Integer, ProductsItemViewHolderFactory> viewHolderFactories) {
        return new ProductsAdapter(picasso, formatter, viewHolderFactories);
    }

    @Provides
    @ProductsScope
    public ProductsItemFormatter formatter() {
        return new ProductsItemFormatterImpl(this.activity);
    }

    @Provides
    @IntoMap
    @IntKey(ProductsItemViewHolder.SMALL)
    ProductsItemViewHolderFactory productsItemViewHolderSmall() {
        return new ProductsItemViewHolderSmallFactory();
    }

    @Provides
    @IntoMap
    @IntKey(ProductsItemViewHolder.BIG)
    ProductsItemViewHolderFactory productsItemViewHolderBig() {
        return new ProductsItemViewHolderBigFactory();
    }
}

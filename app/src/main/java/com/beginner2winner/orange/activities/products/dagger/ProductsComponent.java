package com.beginner2winner.orange.activities.products.dagger;

import com.beginner2winner.orange.activities.products.ProductsActivity;
import com.beginner2winner.orange.app.dagger.AppComponent;

import dagger.Component;

/**
 * Dagger2 Component for Products
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@ProductsScope
@Component(modules = { ProductsModule.class }, dependencies = AppComponent.class)
public interface ProductsComponent {

    void inject(ProductsActivity productsActivity);

}

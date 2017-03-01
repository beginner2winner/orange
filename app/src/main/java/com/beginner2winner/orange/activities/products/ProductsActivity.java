package com.beginner2winner.orange.activities.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.beginner2winner.orange.activities.products.dagger.DaggerProductsComponent;
import com.beginner2winner.orange.activities.products.dagger.ProductsModule;
import com.beginner2winner.orange.activities.products.mvp.ProductsPresenter;
import com.beginner2winner.orange.activities.products.mvp.ProductsView;
import com.beginner2winner.orange.activities.products.ui.ProductsAdapter;
import com.beginner2winner.orange.app.OrangeApplication;

import javax.inject.Inject;

/**
 * Activity (thin for RxMVP) to display Products List
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsActivity extends AppCompatActivity {

    @Inject
    ProductsView view;

    @Inject
    ProductsPresenter presenter;

    @Inject
    ProductsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerProductsComponent.builder()
                .appComponent(OrangeApplication.get(this).component())
                .productsModule(new ProductsModule(this))
                .build()
                .inject(this);

        setContentView(view);
        view.setAdapter(adapter);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

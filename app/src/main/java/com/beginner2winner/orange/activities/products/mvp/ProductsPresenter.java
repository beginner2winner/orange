package com.beginner2winner.orange.activities.products.mvp;

import com.beginner2winner.orange.app.network.model.EndClothingProductsList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * MVP Presenter for Products
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsPresenter {

    private final ProductsView view;
    private final ProductsModel model;

    private Subscription subscription;

    public ProductsPresenter(ProductsView view, ProductsModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {

        this.view.showLoading(true);

        // RxJava2  Subscription to obtain data from network call

        this.subscription = this.model.getProductsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(endClothingProductsList -> {
                   view.setData(endClothingProductsList);
                });
    }

    public void onDestroy() {

        // RxJava2 ensure unsubscribe to prevent memory leak or returns when Activity no longer exists

        if (this.subscription != null && !this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
    }

}

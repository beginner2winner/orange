package com.beginner2winner.orange.activities.products.mvp;

import android.app.Activity;

import com.beginner2winner.orange.app.network.EndClothingNetwork;
import com.beginner2winner.orange.app.network.model.EndClothingProductsList;

import java.util.List;

import rx.Observable;

/**
 * MVP Model for Products
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ProductsModel {

    private final Activity activity;
    private final EndClothingNetwork endClothingNetwork;

    public ProductsModel(Activity activity, EndClothingNetwork endClothingNetwork) {
        this.activity = activity;
        this.endClothingNetwork = endClothingNetwork;
    }

    public Observable<EndClothingProductsList> getProductsList() {
        return endClothingNetwork.getProductsList();
    }

}

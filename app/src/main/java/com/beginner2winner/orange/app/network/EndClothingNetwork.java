package com.beginner2winner.orange.app.network;

import com.beginner2winner.orange.app.network.model.EndClothingProductsList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public interface EndClothingNetwork {

    @GET("/media/ios/response.xml")
    Observable<EndClothingProductsList> getProductsList();

}

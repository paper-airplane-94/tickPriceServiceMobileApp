package com.samsung.bitcoin.client;


import com.samsung.bitcoin.domain.TargetTickPrice;
import com.samsung.bitcoin.domain.TickPrice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TargetTickPriceRetrofitInterfcae {
    @POST("setTargetTickPrice")
    Call<Void> setTargetTickPrice(@Body TargetTickPrice targetTickPrice);
}

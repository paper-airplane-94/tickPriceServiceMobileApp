package com.samsung.bitcoin.client;

import com.samsung.bitcoin.domain.Market;
import com.samsung.bitcoin.domain.TickPrice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarketRetrofitInterface {
    @GET("markets")
    Call<List<Market>> getMarkets();
}

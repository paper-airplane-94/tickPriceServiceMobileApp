package com.samsung.bitcoin.client;

import android.os.AsyncTask;
import android.util.Log;

import com.samsung.bitcoin.domain.TargetTickPrice;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TargetTickPriceSetClientTask extends AsyncTask<Void, Void, Void> {
    private final String url = "http://192.168.0.44:8083/";
    private TargetTickPriceRetrofitInterfcae targetTickPriceRetrofitInterfcae;
    private TargetTickPrice targetTickPrice;

    public TargetTickPriceSetClientTask(TargetTickPrice targetTickPrice) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.targetTickPriceRetrofitInterfcae = retrofit.create(TargetTickPriceRetrofitInterfcae.class);
        this.targetTickPrice = targetTickPrice;
        Log.i("Tick-Price", targetTickPrice.toString());
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            targetTickPriceRetrofitInterfcae.setTargetTickPrice(targetTickPrice).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.samsung.bitcoin.client;

import android.os.AsyncTask;

import com.github.mikephil.charting.charts.LineChart;
import com.samsung.bitcoin.domain.Market;
import com.samsung.bitcoin.domain.TickPrice;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarketClientTask extends AsyncTask<Void, Void, List<Market>> {
    private final String url = "http://192.168.0.44:8083/";
    private MarketRetrofitInterface marketRetrofitInterface;

    public MarketClientTask() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.marketRetrofitInterface = retrofit.create(MarketRetrofitInterface.class);
    }

    @Override
    protected List<Market> doInBackground(Void... voids) {
        List<Market> markets = null;
        try {
            markets = marketRetrofitInterface.getMarkets().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return markets;
    }


    @Override
    protected void onPostExecute(List<Market> markets) {
        super.onPostExecute(markets);
    }

}

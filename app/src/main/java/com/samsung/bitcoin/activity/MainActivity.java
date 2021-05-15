package com.samsung.bitcoin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.samsung.bitcoin.R;
import com.samsung.bitcoin.adapter.MarketViewAdapter;
import com.samsung.bitcoin.client.MarketClientTask;
import com.samsung.bitcoin.decoration.RecyclerViewDecoration;
import com.samsung.bitcoin.domain.Market;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("FCM-Token", FirebaseInstanceId.getInstance().getToken());

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MarketViewAdapter marketViewAdapter = new MarketViewAdapter(getMarkets());
        recyclerView.setAdapter(marketViewAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDecoration(35));

    }

    public List<Market> getMarkets(){
        final MarketClientTask marketClientTask = new MarketClientTask();
        List<Market> markets = null;
        try {
            markets = marketClientTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return markets;
    }
}
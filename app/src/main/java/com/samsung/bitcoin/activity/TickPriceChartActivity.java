package com.samsung.bitcoin.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.google.firebase.iid.FirebaseInstanceId;
import com.samsung.bitcoin.R;
import com.samsung.bitcoin.client.TargetTickPriceSetClientTask;
import com.samsung.bitcoin.client.TickPriceClientTask;
import com.samsung.bitcoin.domain.TargetTickPrice;

import java.util.concurrent.ExecutionException;

public class TickPriceChartActivity extends Activity {
    private TextView textView;
    private Button button;
    private String marketId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        marketId = getIntent().getStringExtra("marketId");
        Toast.makeText(this, marketId, Toast.LENGTH_LONG).show();
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        TickPriceClientTask task = new TickPriceClientTask(lineChart);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        textView = (TextView) findViewById(R.id.targetPriceText);
        button = (Button) findViewById(R.id.setPriceButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Double price = Double.parseDouble(textView.getText().toString());
                TargetTickPrice targetTickPrice = new TargetTickPrice(token, marketId, price);
                Log.i("Tick-Price",targetTickPrice.toString());
                TargetTickPriceSetClientTask targetTickPriceSetClientTask = new TargetTickPriceSetClientTask(targetTickPrice);
                targetTickPriceSetClientTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

}

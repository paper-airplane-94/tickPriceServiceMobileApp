package com.samsung.bitcoin.client;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.samsung.bitcoin.R;
import com.samsung.bitcoin.domain.TickPrice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TickPriceClientTask extends AsyncTask<Void, Void, List<TickPrice>> {
    private final String url = "http://192.168.0.44:8083/";
    private TickPriceRetrofitInterface tickPriceRetrofitInterface;
    private LineChart lineChart;

    public TickPriceClientTask(LineChart lineChart) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.tickPriceRetrofitInterface = retrofit.create(TickPriceRetrofitInterface.class);
        this.lineChart = lineChart;
        initChartOption(lineChart);
    }

    @Override
    protected List<TickPrice> doInBackground(Void... voids) {
        boolean stop = false;
        while (!stop) {
            try {
                Thread.sleep(125);
                tickPriceRetrofitInterface.getTickePriceMarket().enqueue(new Callback<List<TickPrice>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<List<TickPrice>> call, Response<List<TickPrice>> response) {
                        List<TickPrice> tickPrices = response.body();
                        tickPrices.stream().forEach(tickPrice -> addEntry(tickPrice.getTickPrice()));
                    }

                    @Override
                    public void onFailure(Call<List<TickPrice>> call, Throwable t) {

                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void addEntry(double price){
        LineData data = lineChart.getData();

        if(data == null){
            data = new LineData();
            lineChart.setData(data);
        }

        ILineDataSet set = data.getDataSetByIndex(0);

        if(set == null){
            set = createSet();
            data.addDataSet(set);
        }

        data.addEntry(new Entry((float) set.getEntryCount(), (float) price),0);
        data.notifyDataChanged();

        lineChart.notifyDataSetChanged();
        lineChart.setVisibleXRangeMaximum(1000);
        lineChart.moveViewTo(data.getEntryCount(), 50f, YAxis.AxisDependency.LEFT);
    }

    private LineDataSet createSet() {
        LineDataSet lineDataSet = new LineDataSet(null, "Real-time Line Data");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleColor(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);
        return lineDataSet;
    }

    private void initChartOption(LineChart chart){
        chart.invalidate();
        chart.clear();

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxis.setValueFormatter => Customizing 해야함
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = chart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = chart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("KRW-ETC 라인차트");
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDescription(description);
        chart.invalidate();
    }

}

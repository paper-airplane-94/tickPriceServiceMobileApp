package com.samsung.bitcoin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.bitcoin.R;
import com.samsung.bitcoin.activity.TickPriceChartActivity;
import com.samsung.bitcoin.domain.Market;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MarketViewAdapter extends RecyclerView.Adapter<MarketViewAdapter.ViewHolder> {
    private List<Market> markets;

    public MarketViewAdapter(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public MarketViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        final Context ctx = parent.getContext();
        final LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MarketViewAdapter.ViewHolder holder, int position) {
        Market market = markets.get(position);
        holder.onBindView(market);
    }

    @Override
    public int getItemCount() {
        return markets.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView marketId;
        TextView koreaName;
        TextView englishName;
        Button search;

        public ViewHolder(View view){
            super(view);
            marketId = view.findViewById(R.id.title);
            koreaName = view.findViewById(R.id.korea);
            englishName = view.findViewById(R.id.english);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                       Intent intent = new Intent(view.getContext(), TickPriceChartActivity.class);
                       intent.putExtra("marketId", marketId.getText());
                       view.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void onBindView(Market market){
            marketId.setText(market.getMarketId());
            koreaName.setText(market.getKoreaName());
            englishName.setText(market.getEnglishName());
        }


    }
}

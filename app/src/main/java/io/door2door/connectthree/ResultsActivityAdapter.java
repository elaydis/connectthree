package io.door2door.connectthree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

class ResultsActivityAdapter extends RecyclerView.Adapter {
  private ArrayList<String> transportTypes = new ArrayList<>(Arrays.asList("Public Transport", "Public Transport", "Ride-Share"));
  private ArrayList<String> times = new ArrayList<>(Arrays.asList("12:00 -> 13:00", "13:00 -> 15:00", "15:00 -> 18:00"));
  private ArrayList<String> durations = new ArrayList<>(Arrays.asList("1 hour", "2 hours", "3 hours"));
  private ArrayList<String> prices = new ArrayList<>(Arrays.asList("100 €", "200 €", "300 €"));

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.result_card, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((ViewHolder) holder).textView.setText(transportTypes.toArray(new String[transportTypes.size()])[position]);
    ((ViewHolder) holder).textView2.setText(times.toArray(new String[times.size()])[position]);
    ((ViewHolder) holder).textView3.setText(durations.toArray(new String[durations.size()])[position]);
    ((ViewHolder) holder).textView4.setText(prices.toArray(new String[prices.size()])[position]);
  }

  @Override
  public int getItemCount() {
    return 3;
  }
}

class ViewHolder extends RecyclerView.ViewHolder {

  public TextView textView;
  public TextView textView2;
  public TextView textView3;
  public TextView textView4;

  public ViewHolder(View itemView) {
    super(itemView);

    textView = (TextView) itemView.findViewById(R.id.textView);
    textView2 = (TextView) itemView.findViewById(R.id.textView2);
    textView3 = (TextView) itemView.findViewById(R.id.textView3);
    textView4 = (TextView) itemView.findViewById(R.id.textView4);
  }
}

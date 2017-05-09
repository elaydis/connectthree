package io.door2door.connectthree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Arrays;

class ResultsActivityAdapter extends RecyclerView.Adapter<ResultsActivityAdapter.ViewHolder> {
  private ArrayList<String> transportTypes =
      new ArrayList<>(Arrays.asList("Public Transport", "Public Transport", "Ride-Share"));
  private ArrayList<String> durations = new ArrayList<>(Arrays.asList("12", "22", "32"));
  private ArrayList<String> prices = new ArrayList<>(Arrays.asList("1,00€", "2,00€", "3,00€"));
  private final BookButtonClickListener bookButtonClickListener;

  ResultsActivityAdapter(BookButtonClickListener bookButtonClickListener) {
    this.bookButtonClickListener = bookButtonClickListener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.result_card, parent, false);

    return new ViewHolder(view, bookButtonClickListener);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    if (position == 2) {
      holder.rideSharingBookingLayout.setVisibility(View.VISIBLE);
      holder.publicTransportDetailsLayout.setVisibility(View.GONE);
      holder.rideSharingPriceLayout.setVisibility(View.VISIBLE);
    } else {
      holder.rideSharingBookingLayout.setVisibility(View.GONE);
      holder.publicTransportDetailsLayout.setVisibility(View.VISIBLE);
      holder.rideSharingPriceLayout.setVisibility(View.GONE);
    }
    holder.textView.setText(transportTypes.toArray(new String[transportTypes.size()])[position]);
    holder.textView3.setText(durations.toArray(new String[durations.size()])[position]);
    holder.textView4.setText(prices.toArray(new String[prices.size()])[position]);
  }

  @Override
  public int getItemCount() {
    return 3;
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.transportTypeTextView)
    TextView textView;
    @BindView(R.id.travelDurationTextView)
    TextView textView3;
    @BindView(R.id.priceTextView)
    TextView textView4;
    @BindView(R.id.rideSharingBookingLayout)
    LinearLayout rideSharingBookingLayout;
    @BindView(R.id.publicTransportDetailsLayout)
    LinearLayout publicTransportDetailsLayout;
    @BindView(R.id.rideSharingPriceLayout)
    LinearLayout rideSharingPriceLayout;

    private BookButtonClickListener clickListener;

    public ViewHolder(View itemView, BookButtonClickListener clickListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.clickListener = clickListener;
    }

    @OnClick(R.id.button)
    public void onClick() {
      clickListener.onBookButtonClick();
    }
  }
}

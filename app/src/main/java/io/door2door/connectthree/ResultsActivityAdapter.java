package io.door2door.connectthree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Arrays;

class ResultsActivityAdapter extends RecyclerView.Adapter<ResultsActivityAdapter.ViewHolder> {
  private ArrayList<String> transportTypes = new ArrayList<>(
      Arrays.asList("Ride-Share", "Public Transport", "Public Transport", "Public Transport"));
  private ArrayList<String> durations = new ArrayList<>(Arrays.asList("15", "28", "31", "25"));
  private ArrayList<String> prices =
      new ArrayList<>(Arrays.asList("1,00 €", "2,80 €", "2,80 €", "2,80 €"));
  private ArrayList<String> starts = new ArrayList<>(
      Arrays.asList("Rosenthaler Platz", "Rosenthaler Platz", "Rosenthaler Platz",
          "Rosa-Luxemburg  Platz"));
  private ArrayList<String> ends = new ArrayList<>(
      Arrays.asList("Potsdamer Platz", "S+U Potsdamer Platz", "Berlin Potsdamer Platz Bahnhof",
          "S+U Potsdamer Platz"));
  private ArrayList<Integer> summaryImages = new ArrayList<>(
      Arrays.asList(R.drawable.route_summary_1, R.drawable.route_summary_1,
          R.drawable.route_summary_2, R.drawable.route_summary_3));
  private ArrayList<Integer> detailImages = new ArrayList<>(
      Arrays.asList(R.drawable.route_details_1, R.drawable.route_details_1,
          R.drawable.route_details_2, R.drawable.route_details_3));
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
    if (position == 0) {
      holder.rideSharingBookingLayout.setVisibility(View.VISIBLE);
      holder.publicTransportSummaryImageView.setVisibility(View.GONE);
      holder.rideSharingPriceLayout.setVisibility(View.VISIBLE);
    } else {
      holder.rideSharingBookingLayout.setVisibility(View.GONE);
      holder.publicTransportSummaryImageView.setVisibility(View.VISIBLE);
      holder.rideSharingPriceLayout.setVisibility(View.GONE);
    }
    holder.transportTypeTextView.setText(
        transportTypes.toArray(new String[transportTypes.size()])[position]);
    holder.travelDurationTextView.setText(
        durations.toArray(new String[durations.size()])[position]);
    holder.priceTextView.setText(prices.toArray(new String[prices.size()])[position]);
    holder.startTextView.setText(starts.toArray(new String[starts.size()])[position]);
    holder.endTextView.setText(ends.toArray(new String[ends.size()])[position]);
    holder.publicTransportSummaryImageView.setImageResource(
        summaryImages.toArray(new Integer[summaryImages.size()])[position]);
    holder.publicTransportDetailsImageView.setImageResource(
        detailImages.toArray(new Integer[detailImages.size()])[position]);
  }

  @Override
  public int getItemCount() {
    return 4;
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.transportTypeTextView)
    TextView transportTypeTextView;
    @BindView(R.id.travelDurationTextView)
    TextView travelDurationTextView;
    @BindView(R.id.priceTextView)
    TextView priceTextView;
    @BindView(R.id.startTextView)
    TextView startTextView;
    @BindView(R.id.endTextView)
    TextView endTextView;
    @BindView(R.id.publicTransportSummaryImageView)
    ImageView publicTransportSummaryImageView;
    @BindView(R.id.publicTransportDetailsImageView)
    ImageView publicTransportDetailsImageView;
    @BindView(R.id.rideSharingBookingLayout)
    LinearLayout rideSharingBookingLayout;
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

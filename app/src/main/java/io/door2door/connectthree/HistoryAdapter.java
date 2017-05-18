package io.door2door.connectthree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Arrays;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

  private final SuggestionClickListener clickListener;

  private ArrayList<String> suggestionTitles = new ArrayList<>(
      Arrays.asList("Potsdamer Platz", "door2door", "Alexanderplatz", "Rosenthaler Platz",
          "Tegel Airport", "Alexanderplatz", "Rosenthaler Platz", "Tegel Airport", "Alexanderplatz",
          "Rosenthaler Platz", "Tegel Airport"));
  private ArrayList<String> suggestionAddresses = new ArrayList<>(
      Arrays.asList("Potsdamer Platz", "Torstraße 109", "Alexanderstraße 5", "Torstraße 101",
          "Flughafenstraße 1", "Alexanderstraße 5", "Torstraße 101", "Flughafenstraße 1",
          "Alexanderstraße 5", "Torstraße 101", "Flughafenstraße 1"));

  public HistoryAdapter(SuggestionClickListener clickListener) {
    this.clickListener = clickListener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
    return new ViewHolder(view, clickListener);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.suggestionAddress = suggestionAddresses.get(position);
    holder.suggestionTitleTextView.setText(suggestionTitles.get(position));
    holder.suggestionAddressTextView.setText(suggestionAddresses.get(position));
  }

  @Override
  public int getItemCount() {
    return 11;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.suggestionTitleTextView)
    TextView suggestionTitleTextView;
    @BindView(R.id.suggestionAddressTextView)
    TextView suggestionAddressTextView;
    SuggestionClickListener clickListener;
    private String suggestionAddress;

    public ViewHolder(View itemView, SuggestionClickListener clickListener) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.clickListener = clickListener;
    }

    @OnClick(R.id.container)
    void click() {
      clickListener.onSuggestionClick(suggestionAddress);
    }
  }
}

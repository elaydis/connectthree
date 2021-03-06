package io.door2door.connectthree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class PlannerActivity extends FragmentActivity implements OnMapReadyCallback {

  @BindView(R.id.slidingLayout)
  SlidingUpPanelLayout slidingUpPanelLayout;
  @BindView(R.id.destinationEditText)
  EditText destinationEditText;
  @BindView(R.id.historyList)
  RecyclerView historyList;

  private View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }
  };
  private SuggestionClickListener clickListener = new SuggestionClickListener() {
    @Override
    public void onSuggestionClick(String suggestionAddress) {
      destinationEditText.setText(suggestionAddress);
      startResultsActivity(suggestionAddress);
    }
  };

  private void startResultsActivity(String suggestionAddress) {
    Intent intent = new Intent(this, ResultsActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    intent.putExtra(ResultsActivity.SUGGESTION_ADDRESS, suggestionAddress);
    startActivity(intent);
  }

  private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
      if (hasFocus) {
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
      } else {
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
      }
    }
  };

  private TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
      boolean handled = false;
      if (actionId == EditorInfo.IME_ACTION_GO) {
        startResultsActivity(v.getText().toString());
        handled = true;
      }
      return handled;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_planner);
    ButterKnife.bind(this);
    SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    setUpViews();
  }

  private void setUpViews() {
    destinationEditText.setOnClickListener(onClickListener);
    destinationEditText.setOnFocusChangeListener(focusChangeListener);
    historyList.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    historyList.setAdapter(new HistoryAdapter(clickListener));
    slidingUpPanelLayout.setScrollableView(historyList);
    destinationEditText.setOnEditorActionListener(listener);
  }

  @Override
  public void onBackPressed() {
    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    //super.onBackPressed();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    GoogleMap mMap = googleMap;

    // Add a marker in Sydney and move the camera
    LatLng door2door = new LatLng(52.5298727, 13.4028925);
    mMap.addMarker(new MarkerOptions().position(door2door).title("door2door HQ"));
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(door2door, 15));
  }
}

package io.door2door.connectthree;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultsActivity extends FragmentActivity implements OnMapReadyCallback {

  public static final String SUGGESTION_ADDRESS = "suggestion_address";

  @BindView(R.id.destinationEditText)
  EditText destinationEditText;
  @BindView(R.id.slidingLayout)
  SlidingUpPanelLayout slidingUpPanelLayout;

  private GoogleMap mMap;

  private TextView.OnEditorActionListener editTextActionListener =
      new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
          boolean handled = false;
          if (actionId == EditorInfo.IME_ACTION_GO) {
            InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            destinationEditText.clearFocus();
            handled = true;
          }
          return handled;
        }
      };

  private void startRidingActivity() {
    Intent intent = new Intent(this, RideActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
  }

  private BookButtonClickListener bookButtonClickListener = new BookButtonClickListener() {
    @Override
    public void onBookButtonClick() {
      Toast.makeText(ResultsActivity.this, "Go to riding", Toast.LENGTH_SHORT).show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_results);
    ButterKnife.bind(this);

    destinationEditText.setOnEditorActionListener(editTextActionListener);

    SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    DiscreteScrollView scrollView = (DiscreteScrollView) findViewById(R.id.picker);
    scrollView.setAdapter(new ResultsActivityAdapter(bookButtonClickListener));
    scrollView.setOffscreenItems(1);
    //scrollView.setItemTransformer(new ScaleTransformer.Builder().setMaxScale(1.05f)
    //    .setMinScale(0.8f)
    //    .setPivotX(Pivot.X.CENTER)
    //    .setPivotY(Pivot.Y.BOTTOM)
    //    .build());
    scrollView.setOnItemChangedListener(
        new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
          @Override
          public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder,
              int adapterPosition) {
            if (mMap != null) {
              mMap.clear();

              //if (adapterPosition == 0) {
              //  PolylineOptions options = new PolylineOptions();
              //  options.add(new LatLng(52.5298727, 13.4028925));
              //  options.add(new LatLng(52.5096, 13.3759));
              //  mMap.addPolyline(options);
              //}

              if (adapterPosition == 1) {
                PolylineOptions options = new PolylineOptions();
                options.add(new LatLng(52.5298727, 13.4028925));
                options.add(new LatLng(52.5297, 13.4014));
                options.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options);
                PolylineOptions options2 = new PolylineOptions();
                options2.add(new LatLng(52.5297, 13.4014));
                options2.add(new LatLng(52.5250, 13.3922));
                options2.color(Color.parseColor("#E8475A"));
                mMap.addPolyline(options2);
                PolylineOptions options3 = new PolylineOptions();
                options3.add(new LatLng(52.5250, 13.3922));
                options3.add(new LatLng(52.5087, 13.3762));
                options3.color(Color.parseColor("#93C75A"));
                mMap.addPolyline(options3);
                PolylineOptions options4 = new PolylineOptions();
                options4.add(new LatLng(52.5087, 13.3762));
                options4.add(new LatLng(52.5096, 13.3759));
                options4.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options4);
              }

              if (adapterPosition == 2) {
                PolylineOptions options = new PolylineOptions();
                options.add(new LatLng(52.5298727, 13.4028925));
                options.add(new LatLng(52.5297, 13.4014));
                options.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options);
                PolylineOptions options2 = new PolylineOptions();
                options2.add(new LatLng(52.5297, 13.4014));
                options2.add(new LatLng(52.5319, 13.3884));
                options2.color(Color.parseColor("#E8475A"));
                mMap.addPolyline(options2);
                PolylineOptions options3 = new PolylineOptions();
                options3.add(new LatLng(52.5319, 13.3884));
                options3.add(new LatLng(52.5087, 13.3762));
                options3.color(Color.parseColor("#93C75A"));
                mMap.addPolyline(options3);
                PolylineOptions options4 = new PolylineOptions();
                options4.add(new LatLng(52.5087, 13.3762));
                options4.add(new LatLng(52.5096, 13.3759));
                options4.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options4);
              }

              if (adapterPosition == 3) {
                PolylineOptions options = new PolylineOptions();
                options.add(new LatLng(52.5298727, 13.4028925));
                options.add(new LatLng(52.5280, 13.4103));
                options.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options);
                PolylineOptions options2 = new PolylineOptions();
                options2.add(new LatLng(52.5280, 13.4103));
                options2.add(new LatLng(52.5087, 13.3762));
                options2.color(Color.parseColor("#4A90E2"));
                mMap.addPolyline(options2);
                PolylineOptions options3 = new PolylineOptions();
                options3.add(new LatLng(52.5087, 13.3762));
                options3.add(new LatLng(52.5096, 13.3759));
                options3.color(Color.parseColor("#D3D3D3"));
                mMap.addPolyline(options3);
              }
            }
          }
        });

    EditText editText = (EditText) findViewById(R.id.destinationEditText);
    editText.setText(getIntent().getExtras().getString(SUGGESTION_ADDRESS));

    slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
      @Override
      public void onPanelSlide(View panel, float slideOffset) {

      }

      @Override
      public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
          SlidingUpPanelLayout.PanelState newState) {

        destinationEditText.setVisibility(
            newState == SlidingUpPanelLayout.PanelState.EXPANDED ? View.GONE : View.VISIBLE);
      }
    });
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    //LatLng door2door = new LatLng(52.5298727, 13.4028925);
    //mMap.addMarker(new MarkerOptions().position(door2door).title("door2door HQ"));
    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(door2door, 15));
    //PolylineOptions options = new PolylineOptions();
    //options.add(new LatLng(52.5298727, 13.4028925));
    //options.add(new LatLng(52.5096, 13.3759));
    //mMap.addPolyline(options);
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.5197371, 13.3893931), 13));
  }
}

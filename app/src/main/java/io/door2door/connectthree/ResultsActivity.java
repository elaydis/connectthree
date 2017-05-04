package io.door2door.connectthree;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultsActivity extends FragmentActivity implements OnMapReadyCallback {

  public static final String SUGGESTION_ADDRESS = "suggestion_address";

  private GoogleMap mMap;
  private ArrayList<LatLng> coordsStart = new ArrayList<>(
      Arrays.asList(new LatLng(52.5298727, 13.4028925), new LatLng(52.5200, 13.4050),
          new LatLng(52.5298727, 13.4028925)));
  private ArrayList<LatLng> coordsEnd = new ArrayList<>(
      Arrays.asList(new LatLng(52.523297, 13.4200692), new LatLng(51.5074, 0.1278),
          new LatLng(52.523297, 13.4200692)));

  private DialogInterface.OnClickListener bookListener = new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
      startRidingActivity();
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
      AlertDialog alertDialog =
          new AlertDialog.Builder(ResultsActivity.this).setTitle("Pick number of seats")
              .setMessage("2 seats")
              .setPositiveButton("BOOK", bookListener)
              .create();
      alertDialog.show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_results);

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

              PolylineOptions options = new PolylineOptions();
              options.add(coordsStart.toArray(new LatLng[coordsStart.size()])[adapterPosition]);
              options.add(coordsEnd.toArray(new LatLng[coordsEnd.size()])[adapterPosition]);
              mMap.addPolyline(options);
            }
          }
        });

    EditText editText = (EditText) findViewById(R.id.destinationEditText);
    editText.setText(getIntent().getExtras().getString(SUGGESTION_ADDRESS));
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    LatLng door2door = new LatLng(52.5298727, 13.4028925);
    mMap.addMarker(new MarkerOptions().position(door2door).title("door2door HQ"));
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(door2door, 15));
  }
}

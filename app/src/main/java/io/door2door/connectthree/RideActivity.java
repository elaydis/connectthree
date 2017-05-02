package io.door2door.connectthree;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class RideActivity extends FragmentActivity implements OnMapReadyCallback {

  @BindView(R.id.slidingLayout)
  SlidingUpPanelLayout slidingUpPanelLayout;

  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ride);
    ButterKnife.bind(this);
    SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    context = this;
    setUpViews();
  }

  private void setUpViews() {

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

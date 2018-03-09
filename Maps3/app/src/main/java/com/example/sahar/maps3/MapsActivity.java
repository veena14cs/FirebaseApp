package com.example.sahar.maps3;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

private GoogleMap mMap;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

}
/**
 * Called when the map is ready.
 */
        @Override
        public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                // Add a marker in Sydney, Australia, and move the camera.
//                LatLng sydney = new LatLng(-34, 151);
//                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//                Polygon polygon = mMap.addPolygon(new PolygonOptions()
//                        .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
//                        .strokeColor(Color.RED)
//                        .fillColor(Color.BLUE));
            drawPolygon();
        }
    private void drawPolygon() {
        LatLng point1 = new LatLng( 15.806135,74.490329);
        LatLng point2 = new LatLng( 15.806165,74.490596);
        LatLng point3= new LatLng( 15.806659,74.490529);
        LatLng point4 = new LatLng( 15.806631,74.490293);

        PolygonOptions options = new PolygonOptions();
        options.add( point1, point2, point3 ,point4);

        options.fillColor( getResources()
                .getColor( R.color.fill_color ) );
        options.strokeColor( getResources()
                .getColor( R.color.stroke_color ) );
        options.strokeWidth( 2 );


        CameraPosition position = CameraPosition.builder().target( point1 ).zoom( 18f ).bearing( 0.0f ).tilt( 0.0f ).build();
        mMap.addPolygon( options );
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

    }
}



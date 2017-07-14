package com.voidx.seek;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.voidx.seek.Session.lastCurrentLocation;
import static com.voidx.seek.Session.userHashMap;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private OnFragmentInteractionListener mListener;

    private View mView;
    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private Context context;

    private LocationManager locationManager;
    private LocationListener locationListener;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mView = view;
        context = mView.getContext();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = mView.findViewById(R.id.map_view);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                lastCurrentLocation = location;

                LatLng currentLatLng = new LatLng(lastCurrentLocation.getLatitude(), lastCurrentLocation.getLongitude());

                LatLngBounds.Builder builder = new LatLngBounds.Builder();

                builder.include(currentLatLng);
                for(User user: userHashMap.values()) {
                    builder.include(new LatLng(user.latitude, user.longitude));
                    addUserMarker(user);
                    Log.d("user-----", user.firstName);
                }
                LatLngBounds bounds = builder.build();

                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 350));

//                CameraPosition cameraPosition = CameraPosition.builder().target(currentLatLng).zoom(17).bearing(0).tilt(4).build();
//                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PermissionCode.MY_PERMISSION_ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PermissionCode.MY_PERMISSION_ACCESS_COARSE_LOCATION);

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMapFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                context, R.raw.default_map_style);
        mGoogleMap.setMapStyle(style);
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(false);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PermissionCode.MY_PERMISSION_ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PermissionCode.MY_PERMISSION_ACCESS_COARSE_LOCATION);

            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        LatLng currentLatLng = new LatLng(lastCurrentLocation.getLatitude(), lastCurrentLocation.getLongitude());

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 13));

//        mGoogleMap.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(currentLatLng));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMapFragmentInteraction(Uri uri);
    }

    public void addUserMarker(User user){
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(120, 120, conf);
        Canvas canvas1 = new Canvas(bmp);

        //Paint defines the text color, stroke width and size
        Paint color = new Paint();
        color.setTextSize(35);
        color.setColor(Color.BLACK);

        //Modify canvas
        Bitmap resizedProfileBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                ProfilePictureHandler.getProfilePictureResourceId(user.id)), 100, 100, true);
        Bitmap croppedProfileBitmap = ImageUtil.cropAsCircle(resizedProfileBitmap);
        canvas1.drawBitmap(croppedProfileBitmap, 0, 0, color);

        LatLng markerLatLng = new LatLng(user.latitude, user.longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(markerLatLng)
                .title(user.firstName + " " + user.lastName)
//                .snippet(mainSubject + "\n" + distance + " km")
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .anchor(0.5f, 1);
        mGoogleMap.addMarker(markerOptions);
    }
}
